package com.inti.SpringBootValidationThymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.SpringBootValidationThymeleaf.model.Utilisateur;
import com.inti.SpringBootValidationThymeleaf.repository.UtilisateurRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController
{
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	
	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}
	
	@GetMapping("/creerUtilisateur")
	public String creerUtilisateur()
	{
		
		return "creerUtilisateur";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(Utilisateur user)
	{
		log.info("user : " + user);

		
		utilisateurRepository.save(user);
		
		return "redirect:/listeUtilisateur";
	}
	
	@GetMapping("/listeUtilisateur")
	public String listeUtilisateur()
	{
		
		return "listeUtilisateur";
	}

}
