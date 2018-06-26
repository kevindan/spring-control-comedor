package com.springcontrolcomedor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

	@GetMapping("/productos")
	public String producto() {
		return "productos";
	}

}
