package com.springcontrolcomedor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

	@GetMapping("usuarios")
	public String usuario() {
		return "usuarios";
	}

}
