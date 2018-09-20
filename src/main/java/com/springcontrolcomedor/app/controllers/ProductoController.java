package com.springcontrolcomedor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/productos")
public class ProductoController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String producto() {
		return "productos";
	}

}
