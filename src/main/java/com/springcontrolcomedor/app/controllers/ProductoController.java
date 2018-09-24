package com.springcontrolcomedor.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springcontrolcomedor.app.entity.Producto;
import com.springcontrolcomedor.app.entity.TipoProducto;
import com.springcontrolcomedor.app.service.IProductoService;
import com.springcontrolcomedor.app.service.ITipoProductoService;
import com.springcontrolcomedor.app.util.paginator.PageRender;

@Controller
@RequestMapping(value = "/productos")
@SessionAttributes("producto")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	@Autowired
	private ITipoProductoService tipoProductoService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Producto producto = new Producto();
		List<TipoProducto> tipoProductos = tipoProductoService.findByActivos();

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Producto> productos = productoService.findByActivos(pageRequest);

		int numElementos = (int) productos.getTotalElements();
		String mensajeTabla = "";

		if (numElementos == 0) {

			mensajeTabla = "No hay productos registrados";
		}

		PageRender<Producto> pageRender = new PageRender<>("", productos);

		model.addAttribute("producto", producto);
		model.addAttribute("tipoProductos", tipoProductos);
		model.addAttribute("numeroElementos", numElementos);
		model.addAttribute("mensajeTabla", mensajeTabla);
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);

		return "productos";
	}

	@RequestMapping(value = { "/filtrar/{descripcion}", "/filtrar/" }, method = RequestMethod.GET)
	private String filtrarPorDescripcion(@RequestParam(name = "page", defaultValue = "0") int page,
			@PathVariable(value = "descripcion", required = false) String descripcion, Model model) {

		Producto producto = new Producto();
		List<TipoProducto> tipoProductos = tipoProductoService.findByActivos();

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Producto> productos = null;

		if (descripcion != null) {

			productos = productoService.findByDescripcion(descripcion, pageRequest);

		} else {

			return "redirect:/productos";
		}

		int numElementos = (int) productos.getTotalElements();
		String mensajeTabla = "";

		if (numElementos == 0) {

			mensajeTabla = "No se han encontrado coincidencias";
		}

		PageRender<Producto> pageRender = new PageRender<>("", productos);

		model.addAttribute("producto", producto);
		model.addAttribute("tipoProductos", tipoProductos);
		model.addAttribute("numeroElementos", numElementos);
		model.addAttribute("mensajeTabla", mensajeTabla);
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);

		return "productos";

	}

	@RequestMapping(value = "/grabar", method = RequestMethod.POST)
	public String grabar(@Valid Producto producto, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		
		System.err.println(producto.toString());
		System.out.println(result.getAllErrors());
				
		if (result.hasErrors()) {

			Pageable pageRequest = PageRequest.of(0, 5);

			Page<Producto> productos = productoService.findByActivos(pageRequest);
			List<TipoProducto> tipoProductos = tipoProductoService.findByActivos();
			
			int numElementos = (int) productos.getTotalElements();
			String mensajeTabla = "";

			if (numElementos == 0) {

				mensajeTabla = "No se han encontrado coincidencias";
			}

			PageRender<Producto> pageRender = new PageRender<>("", productos);

			model.addAttribute("titulo", "Listado de Productos");
			model.addAttribute("numeroElementos", numElementos);
			model.addAttribute("mensajeTabla", mensajeTabla);
			model.addAttribute("productos", productos);
			model.addAttribute("tipoProductos", tipoProductos);
			model.addAttribute("page", pageRender);

			return "productos";
		}
		

		String mensajaeFlash = (producto.getIdProducto() != null) ? "¡Producto editado con éxito!"
				: "¡Producto grabado con éxito!";

		productoService.save(producto);
		flash.addFlashAttribute("success", mensajaeFlash);
		status.setComplete();
		return "redirect:/productos";

	}

}
