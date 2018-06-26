package com.springcontrolcomedor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocalController {

	@GetMapping("/locales")
	public String local() {
		return "locales";
	}

}
