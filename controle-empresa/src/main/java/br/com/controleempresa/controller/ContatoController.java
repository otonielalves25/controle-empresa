package br.com.controleempresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.controleempresa.modelo.Contato;
import br.com.controleempresa.repository.ContatoRepository;
import br.com.controleempresa.repository.EmpresaRepository;

@Controller
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	ContatoRepository contatoRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@GetMapping("")
	public String inicia(Model model) {
		model.addAttribute("listagem", contatoRepository.findAll());
		return "contato-lista";
	}

	@GetMapping("/limpar")
	public String limpar(Model model) {
		model.addAttribute("contato", new Contato());
		model.addAttribute("empresas", empresaRepository.findAll());
		return "contato-cad";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Model model, Contato contato) {

		Contato objeto2 = contatoRepository.findByNome(contato.getNome());

		if (objeto2 != null && objeto2.getId() != contato.getId()) {
			model.addAttribute("msg", "Status já cadatrado");
			model.addAttribute("contato", contato);
			return "contato-cad";

		}

		contatoRepository.save(contato);
		model.addAttribute("listagem", contatoRepository.findAll());
		model.addAttribute("msg", "Cadastrado com Sucesso");
		return "contato-lista";
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public String deletar(Model model, @PathVariable("id") Long codigo) {
		contatoRepository.deleteById(codigo);
		model.addAttribute("listagem", contatoRepository.findAll());
		return "contato-lista";
	}

	@RequestMapping(value = "/retorna/{id}", method = RequestMethod.GET)
	public String buscarPorId(@PathVariable("id") Long id, Model model) {
		model.addAttribute("contato", contatoRepository.findById(id));
		model.addAttribute("empresas", empresaRepository.findAll());
		return "contato-cad";
	}

}
