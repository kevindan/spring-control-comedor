package com.springcontrolcomedor.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springcontrolcomedor.app.service.IComensalService;

@Controller
public class IndexController {

	@Autowired
	private IComensalService comensalService;

	@GetMapping(value = { "/index", "/" })
	public String inicio(Model model) {
				
		Long numeroComensales = comensalService.cantidadComensales();
		
		model.addAttribute("numeroComensales",numeroComensales);
		
		return "index";
	}

}
