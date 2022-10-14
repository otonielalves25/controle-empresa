package br.com.controleempresa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.controleempresa.modelo.Empresa;
import br.com.controleempresa.repository.EmpresaRepository;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	EmpresaRepository empresaRepository;

	@GetMapping("")
	public String inicia(Model model) {
		model.addAttribute("listagem", empresaRepository.findAll());
		return "empresa-lista";
	}

	@GetMapping("/limpar")
	public String limpar(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresa-cad";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Model model, Empresa empresa) {

		Empresa objeto2 = empresaRepository.findByNome(empresa.getNome());

		if (objeto2 != null && objeto2.getId() != empresa.getId()) {
			model.addAttribute("msg", "Status já cadatrado");
			model.addAttribute("empresa", empresa);
			return "empresa-cad";

		}

		empresa.getEndereco().setEmpresa(empresa);

		empresaRepository.save(empresa);
		model.addAttribute("listagem", empresaRepository.findAll());
		model.addAttribute("msg", "Cadastrado com Sucesso");
		return "empresa-lista";
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public String deletar(Model model, @PathVariable("id") Long codigo) {
		empresaRepository.deleteById(codigo);
		model.addAttribute("listagem", empresaRepository.findAll());
		return "empresa-lista";
	}

	@RequestMapping(value = "/retorna/{id}", method = RequestMethod.GET)
	public String buscarPorId(@PathVariable("id") Long id, Model model) {
		model.addAttribute("empresa", empresaRepository.findById(id));
		return "empresa-cad";
	}

	@RequestMapping(value = "/detalhe/{id}", method = RequestMethod.GET)
	public String detalhe(@PathVariable("id") Long id, Model model) {
		Optional<Empresa> empOp = empresaRepository.findById(id);
		if (empOp.isPresent()) {
			model.addAttribute("empresa", empOp.get());
		}	
		return "empresa-detalhe";
	}

}
