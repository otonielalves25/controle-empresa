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
import br.com.controleempresa.modelo.TipoEmpresa;
import br.com.controleempresa.repository.TipoRepository;

@Controller
@RequestMapping("/tipoempresa")
public class TipoController {

	@Autowired
	TipoRepository tipoRepository;

	@GetMapping("")
	public String inicia(Model model) {
		model.addAttribute("tipoempresa", new Status());
		model.addAttribute("listagem", tipoRepository.findAll());
		return "tipo-cad";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Model model, TipoEmpresa objeto) {

		Status tipoempresa2 = tipoRepository.findByNome(objeto.getNome());

		if (tipoempresa2 != null && tipoempresa2.getId() != objeto.getId()) {
			model.addAttribute("msg", "Status já cadatrado");
			model.addAttribute("tipoempresa", objeto);
			model.addAttribute("listagem", tipoRepository.findAll());
			return "tipo-cad";

		}

		tipoRepository.save(objeto);
		model.addAttribute("tipoempresa", new Status());
		model.addAttribute("listagem", tipoRepository.findAll());
		model.addAttribute("msg", "Cadastrado com Sucesso");
		return "tipo-cad";
	}

	@GetMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("tipoempresa", new Status());
		model.addAttribute("listagem", tipoRepository.findAll());
		return "tipo-cad";
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public String deletar(Model model, @PathVariable("id") Long codigo) {
		tipoRepository.deleteById(codigo);
		model.addAttribute("tipoempresa", new Status());
		model.addAttribute("listagem", tipoRepository.findAll());
		return "tipo-cad";
	}

	@RequestMapping(value = "/retorna/{id}", method = RequestMethod.GET)
	public String buscarPorId(@PathVariable("id") Long id, Model model) {
		model.addAttribute("tipoempresa", tipoRepository.findById(id));
		model.addAttribute("listagem", tipoRepository.findAll());
		return "tipo-cad";
	}

}
