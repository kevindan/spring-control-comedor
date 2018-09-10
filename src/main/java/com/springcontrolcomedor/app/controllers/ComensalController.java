package com.springcontrolcomedor.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springcontrolcomedor.app.entity.Comensal;
import com.springcontrolcomedor.app.service.IComensalService;
import com.springcontrolcomedor.app.util.paginator.PageRender;

@Controller
@RequestMapping(value = "/comensales")
@SessionAttributes("comensal")
public class ComensalController {

	@Autowired
	private IComensalService comensalService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	private String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Comensal comensal = new Comensal();

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Comensal> comensales = comensalService.findByActivos(0, pageRequest);

		int numElementos = (int) comensales.getTotalElements();

		PageRender<Comensal> pageRender = new PageRender<>("", comensales);

		model.addAttribute("comensal", comensal);
		model.addAttribute("numeroElementos", numElementos);
		model.addAttribute("titulo", "Listado de Comensales");
		model.addAttribute("comensales", comensales);
		model.addAttribute("page", pageRender);

		return "comensales";
	}

	@RequestMapping(value = { "/filtrar/{apellidoPaterno}", "/filtrar/" }, method = RequestMethod.GET)
	private String filtrarPorApellidoPaterno(@RequestParam(name = "page", defaultValue = "0") int page,
			@PathVariable(value = "apellidoPaterno", required = false) String apellidoPaterno, Model model) {

		Comensal comensal = new Comensal();

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Comensal> comensales = null;

		if (apellidoPaterno != null) {

			comensales = comensalService.findbySurname(apellidoPaterno, 0, pageRequest);
		} else {

			return "redirect:/comensales";
		}

		int numElementos = (int) comensales.getTotalElements();

		PageRender<Comensal> pageRender = new PageRender<>("", comensales);

		model.addAttribute("comensal", comensal);
		model.addAttribute("numeroElementos", numElementos);
		model.addAttribute("titulo", "Listado de Comensales");
		model.addAttribute("comensales", comensales);
		model.addAttribute("page", pageRender);

		return "comensales";

	}

	// Metodos utilizando mediante petición asíncrona (AJAX)
	@GetMapping(value = "/buscar/{idComensal}", produces = { "application/json" })
	public @ResponseBody Comensal buscarComensal(@PathVariable Long idComensal) {
		return comensalService.finOne(idComensal);
	}

	@RequestMapping(value = "/buscarcomensaldni", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody String buscarComensalDni(String dni) {

		boolean valid = false;

		Comensal comensal = comensalService.findByDni(dni);

		if (comensal == null) {

			valid = true;
		}

		System.out.println(dni);

		return "{\"valid\":" + valid + "}";
	}
	// ------------------------------------------------------

	@RequestMapping(value = "/grabar", method = RequestMethod.POST)
	public String grabar(@Valid Comensal comensal, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {

			Pageable pageRequest = PageRequest.of(0, 5);

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
		status.setComplete();
		return "redirect:/comensales";
	}

	@RequestMapping(value = "/eliminar/{idComensal}")
	public String eliminar(@PathVariable(value = "idComensal") Long idComensal, RedirectAttributes flash) {

		if (idComensal > 0) {

			comensalService.eliminarComensal(1, idComensal);
			flash.addFlashAttribute("success", "¡Comensal eliminado con éxito!");
		}

		return "redirect:/comensales";
	}

}
