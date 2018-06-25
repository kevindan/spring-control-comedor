package com.springcontrolcomedor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsumoController {

	@GetMapping("/consumo")
	public String consumo(){
		return "consumo";
	}

}
