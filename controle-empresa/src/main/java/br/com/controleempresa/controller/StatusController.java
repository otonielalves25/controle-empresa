package br.com.controleempresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.controleempresa.modelo.Status;
import br.com.controleempresa.repository.StatusRepository;

@Controller
@RequestMapping("/status")
public class StatusController {

	@Autowired
	StatusRepository statusRepository;

	@GetMapping("")
	public String inicia(Model model) {
		model.addAttribute("status", new Status());
		model.addAttribute("listaStatus", statusRepository.findAll());
		return "status/status-cad";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Model model, Status status) {

		Status status2 = statusRepository.findByNome(status.getNome());

	
		

		if (status2 != null) {
			model.addAttribute("msg", "Status já cadatrado");
			model.addAttribute("status", status);
			model.addAttribute("listaStatus", statusRepository.findAll());
			return "status/status-cad";

		}

		statusRepository.save(status);
		model.addAttribute("status", new Status());
		model.addAttribute("listaStatus", statusRepository.findAll());
		model.addAttribute("msg", "Cadastrado com Sucesso");
		return "status/status-cad";
	}

	@GetMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("status", new Status());
		model.addAttribute("listaStatus", statusRepository.findAll());
		return "status/status-cad";
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public String deletar(Model model, @PathVariable("id") Long codigo) {
		statusRepository.deleteById(codigo);
		model.addAttribute("status", new Status());
		model.addAttribute("listaStatus", statusRepository.findAll());
		return "status/status-cad";
	}

	@RequestMapping(value = "/retorna/{id}", method = RequestMethod.GET)
	public String buscarPorId(@PathVariable("id") Long id, Model model) {
		model.addAttribute("status", statusRepository.findById(id));
		model.addAttribute("listaStatus", statusRepository.findAll());
		return "status/status-cad";
	}

}
