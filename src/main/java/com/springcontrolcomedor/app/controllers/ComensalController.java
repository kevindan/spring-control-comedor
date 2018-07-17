package com.springcontrolcomedor.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springcontrolcomedor.app.entity.Comensal;
import com.springcontrolcomedor.app.service.IComensalService;
import com.springcontrolcomedor.app.util.paginator.PageRender;

@Controller
@RequestMapping(value = "/comensales")
public class ComensalController {

	@Autowired
	private IComensalService comensalService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	private String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Comensal comensal = new Comensal();

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Comensal> comensales = comensalService.findAll(pageRequest);

		PageRender<Comensal> pageRender = new PageRender<>("", comensales);

		model.addAttribute("comensal", comensal);
		model.addAttribute("titulo", "Listado de Comensales");
		model.addAttribute("comensales", comensales);
		model.addAttribute("page", pageRender);

		return "comensales";
	}

	@RequestMapping(value = "/editar/{idComensal}")
	public String editar(@PathVariable(value = "idComensal") Long idComensal,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model, RedirectAttributes flash) {

		Comensal comensal = null;

		if (idComensal > 0) {

			comensal = comensalService.finOne(idComensal);

			if (comensal == null) {

				flash.addFlashAttribute("error", "¡El id del comensal no existe en la base de datos!");
				return "redirect:/comensales";
			}

		} else {

			flash.addFlashAttribute("error", "¡El id del comensal no puede ser 0 o nulo!");
			return "redirect:/comensales";
		}

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Comensal> comensales = comensalService.findAll(pageRequest);

		PageRender<Comensal> pageRender = new PageRender<>("", comensales);

		model.addAttribute("comensal", comensal);
		model.addAttribute("titulo", "Listado de Comensales");
		model.addAttribute("comensales", comensales);
		model.addAttribute("page", pageRender);

		return "comensales";
	}

	@RequestMapping(value = "/grabar", method = RequestMethod.POST)
	public String grabar(@Valid Comensal comensal, @RequestParam(name = "page", defaultValue = "0") int page,
			BindingResult result, Model model, RedirectAttributes flash) {

		if (result.hasErrors()) {

			Pageable pageRequest = PageRequest.of(page, 5);

			Page<Comensal> comensales = comensalService.findAll(pageRequest);

			PageRender<Comensal> pageRender = new PageRender<>("", comensales);

			model.addAttribute("titulo", "Listado de Comensales");
			model.addAttribute("comensales", comensales);
			model.addAttribute("page", pageRender);

			return "comensales";
		}

		String mensajaeFlash = (comensal.getIdComensal() != null) ? "¡Comensal editado con éxito!"
				: "¡Comensal grabado con éxito!";

		comensalService.save(comensal);
		flash.addFlashAttribute("success", mensajaeFlash);
		return "redirect:/comensales";
	}

	@RequestMapping(value = "/eliminar/{idComensal}")
	public String eliminar(@PathVariable(value = "idComensal") Long idComensal, RedirectAttributes flash) {

		if (idComensal > 0) {

			comensalService.delete(idComensal);
			flash.addFlashAttribute("success", "¡Comensal eliminado con éxito!");
		}

		return "redirect:/comensales";
	}

}
