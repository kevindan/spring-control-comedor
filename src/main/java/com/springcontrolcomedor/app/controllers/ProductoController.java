package com.springcontrolcomedor.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
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

		if (result.hasErrors()) {

			Pageable pageRequest = PageRequest.of(0, 5);

			Page<Producto> productos = productoService.findByActivos(pageRequest);
			List<TipoProducto> tipoProductos = tipoProductoService.findByActivos();

			int numElementos = (int) productos.getTotalElements();
			String mensajeTabla = "";

			if (numElementos == 0) {

				mensajeTabla = "No hay productos registrados";
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

		String mensajeFlash = (producto.getIdProducto() != null) ? "¡Producto editado con éxito!"
				: "¡Producto grabado con éxito!";

		productoService.save(producto);
		flash.addFlashAttribute("success", mensajeFlash);
		status.setComplete();
		return "redirect:/productos";

	}

	@GetMapping(value = "/buscar/{idProducto}", produces = { "application/json" })
	public @ResponseBody Producto buscarProducto(@PathVariable Long idProducto) {
		return productoService.findOne(idProducto);
	}

	@RequestMapping(value = "/buscarproductodescripcion", method = RequestMethod.POST, produces = {
			"application/json" })
	public @ResponseBody String buscarProductoDescripcion(String descripcion) {

		boolean valid = false;

		Producto producto = productoService.findByDescripcion(descripcion);

		if (producto == null) {

			valid = true;
		}

		return "{\"valid\":" + valid + "}";
	}

	@GetMapping(value = "/listar", produces = { "application/json" })
	public @ResponseBody List<Producto> listarProductos() {

		return productoService.findByActivos();
	}

	@GetMapping(value = "/actualizaalerta/{idProducto}/{alerta}", produces = { "application/json" })
	public @ResponseBody int actualizaAlerta(@PathVariable Long idProducto, @PathVariable int alerta) {

		int estado = 0;
		Producto producto = productoService.findOne(idProducto);

		if (producto == null) {
			System.out.println(estado);
		} else {
			productoService.actualizaAlerta(alerta, idProducto);
			estado = 1;

		}
		return estado;
	}

	@RequestMapping(value = "/eliminar/{idProducto}")
	public String eliminar(@PathVariable(value = "idProducto") Long idProducto, RedirectAttributes flash) {

		if (idProducto > 0) {

			productoService.eliminarProducto(idProducto);
			flash.addFlashAttribute("success", "¡Producto eliminado con éxito!");
		}

		return "redirect:/productos";
	}

	@RequestMapping(value = "/actualizarstock/{idProducto}/{stockActual}", method = RequestMethod.GET)
	public String actualizarStock(@PathVariable Long idProducto, @PathVariable int stockActual,
			RedirectAttributes flash) {

		String mensajeFlash = "¡Stock actualizado con éxito!";

		productoService.actualizaStock(stockActual, idProducto);
		flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/productos";
	}

	@RequestMapping(value = "/grabarimagen", method = RequestMethod.POST)
	public String grabarImagen(@RequestParam("idproductoimagen") long idproducto, @RequestParam("imagen") MultipartFile imagen,
			RedirectAttributes flash) {

		System.out.println(idproducto);

		if (!imagen.isEmpty()) {

			String rootPath = "C://sistemaweb//images//productos";

			try {

				byte[] bytes = imagen.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytes);

				productoService.guardarImagen(imagen.getOriginalFilename(),idproducto);
				flash.addFlashAttribute("info",
						"Has subido correctamente la imagen " + imagen.getOriginalFilename() + "");
				System.out.println("Id : " + idproducto + ", Imagen : " + imagen.getOriginalFilename());

				return "redirect:/productos";

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		flash.addFlashAttribute("warning","Debe seleccionar una imagen antes de presionar 'Actualizar'");
		return "redirect:/productos";
	}

}