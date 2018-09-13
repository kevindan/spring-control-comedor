package com.springcontrolcomedor.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springcontrolcomedor.app.entity.Comensal;
import com.springcontrolcomedor.app.service.IComensalService;

@Controller
public class IndexController {

	@Autowired
	private IComensalService comensalService;

	@GetMapping(value = { "/index", "/" })
	public String inicio(Model model) {
		
		List<Comensal> comensales = comensalService.findByActivos();
		int numeroComensales = comensales.size();
		
		model.addAttribute("numeroComensales",numeroComensales);
		
		return "index";
	}

}
