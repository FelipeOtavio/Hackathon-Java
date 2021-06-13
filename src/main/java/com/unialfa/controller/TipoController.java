package com.unialfa.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unialfa.model.Tipo;
import com.unialfa.repository.TipoRepository;
import com.unialfa.service.LoginService;

@Controller
@RequestMapping("/tipo")
public class TipoController {

	@Autowired
	TipoRepository repository;
	
	//Serviço de validação usuario logado
	@Autowired
	LoginService login;

	@RequestMapping("lista")
	public String abrirLista(Model model) {
		model.addAttribute("tipos", repository.findAll());

		return login.getIsLogged() ? "tipo/lista" : "redirect:/login";
	}

	@GetMapping("/formulario")
	public String abrirFormulario(Tipo tipo, Model model) {
		
		return login.getIsLogged() ? "tipo/formulario" : "redirect:/login";
	}
	
	@GetMapping("/editar")
	public String editar(@PathParam(value = "id") Integer id, Model model) {
		Tipo t = repository.getById(id);
		model.addAttribute("tipo", t);

		return login.getIsLogged() ? "tipo/formulario" : "redirect:/login";
	}
	

	@PostMapping("salvar")
	public String salvar(Tipo tipo) {
		repository.save(tipo);
		return "redirect:lista";
	}

	@PostMapping("editar/salvar")
	public String atualizar(Tipo tipo) {
		repository.save(tipo);
		return "redirect:../lista";
	}

	@GetMapping(value = "excluir")
	public String excluir(@PathParam(value = "id") Integer id) {
		repository.deleteById(id);
		return "redirect:../lista";
	}

}
