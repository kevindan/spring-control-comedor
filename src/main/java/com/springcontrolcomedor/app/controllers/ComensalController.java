package com.springcontrolcomedor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComensalController {

	@GetMapping("/comensal")
	private String comensal() {
		return "comensal";
	}

}
