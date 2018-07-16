package com.springcontrolcomedor.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springcontrolcomedor.app.dao.IComensalDao;
import com.springcontrolcomedor.app.entity.Comensal;
import com.springcontrolcomedor.app.service.IComensalService;

@Controller
@RequestMapping(value = "/comensales")
public class ComensalController {

	@Autowired
	private IComensalService comensalService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	private String listar(Model model) {

		Comensal comensal = new Comensal();

		model.addAttribute("comensal", comensal);
		model.addAttribute("titulo", "Listado de Comensales");
		model.addAttribute("comensales", comensalService.findAll());

		return "comensales";
	}

	@RequestMapping(value = "/editar/{idComensal}")
	public String editar(@PathVariable(value = "idComensal") Long idComensal, Model model) {

		Comensal comensal = null;

		if (idComensal > 0) {

			comensal = comensalService.finOne(idComensal);
			
		} else {

			return "redirect:/comensales";
		}

		model.addAttribute("comensal", comensal);
		model.addAttribute("titulo", "Listado de Comensales");
		model.addAttribute("comensales", comensalService.findAll());

		return "comensales";
	}

	@RequestMapping(value = "/grabar", method = RequestMethod.POST)
	public String grabar(@Valid Comensal comensal, BindingResult result, Model model) {

		if (result.hasErrors()) {

			model.addAttribute("titulo", "Listado de Comensales");
			model.addAttribute("comensales", comensalService.findAll());

			return "comensales";
		}

		comensalService.save(comensal);
		return "redirect:/comensales";
	}

	@RequestMapping(value = "/eliminar/{idComensal}")
	public String eliminar(@PathVariable(value = "idComensal") Long idComensal) {

		if (idComensal > 0) {

			comensalService.delete(idComensal);
		}

		return "redirect:/comensales";
	}

}
