package com.unialfa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unialfa.model.Usuario;
import com.unialfa.service.LoginService;

@Controller
public class IndexController {

	@Autowired
	LoginService login;
	
	@RequestMapping("/")
	public String iniciar(Model model) {
		
		model.addAttribute("isValid", login.getIsLogged() ? "T" : "F");
		model.addAttribute("usuLogado", login.getUsuarioLogado());
		
		return login.getIsLogged() ? "index" : "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String abrirLogin(Model model) {
	
		return  login.getIsLogged() ? "redirect:/" : "login";
	}
	
	@PostMapping("/logar")
	public String logar(Usuario user, Model model) {
		login.logar(user);

		return "redirect:/";
	}
	
	@GetMapping("/deslogar")
	public String desLogar(Model model) {
		login.desLogar();
		
		return "redirect:/";
	}	
}
