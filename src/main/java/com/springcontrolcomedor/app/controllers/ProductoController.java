package com.springcontrolcomedor.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springcontrolcomedor.app.entity.Producto;
import com.springcontrolcomedor.app.entity.TipoProducto;
import com.springcontrolcomedor.app.service.IProductoService;
import com.springcontrolcomedor.app.service.ITipoProductoService;
import com.springcontrolcomedor.app.util.paginator.PageRender;

@Controller
@RequestMapping(value = "/productos")
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

}
