package com.springcontrolcomedor.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springcontrolcomedor.app.dao.IComensalDao;
import com.springcontrolcomedor.app.entity.Comensal;

@Controller
public class ComensalController {

	@Autowired
	private IComensalDao comensalDao;

	@RequestMapping(value = "/comensales", method = RequestMethod.GET)
	private String listar(Model model) {

		Comensal comensal = new Comensal();
		model.addAttribute("comensal", comensal);
		model.addAttribute("titulo", "Listado de Comensales");
		model.addAttribute("comensales", comensalDao.findAll());
		return "comensales";
	}

	@RequestMapping(value = "/comensales", method = RequestMethod.POST)
	public String guardar(@Valid Comensal comensal, BindingResult result, Model model) {

		if (result.hasErrors()) {
					

			return "comensales";
		}

		comensalDao.save(comensal);
		return "redirect:comensales";

	}

}
