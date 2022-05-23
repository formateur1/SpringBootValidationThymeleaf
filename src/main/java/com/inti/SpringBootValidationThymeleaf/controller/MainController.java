package com.inti.SpringBootValidationThymeleaf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public String creerUtilisateur(Utilisateur utilisateur)
	{
		
		return "creerUtilisateur";
	}
	
	@PostMapping("/creerUtilisateur")
	public String saveUser(@Valid Utilisateur utilisateur, BindingResult br)
	{
		for (FieldError fe : br.getFieldErrors())
		{
			System.out.println(fe);
		}
		
		if(br.hasErrors())
		{
			return "redirect:/creerUtilisateur";
		}
		
//		String nom = utilisateur.getNom();
		
//		if(nom.length() < 3 && nom.length() > 100)
//		{
//			return "redirect:/creerUtilisateur";
//		}
		
		log.info("user : " + utilisateur);
		
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		String mdpCrypte = b.encode(utilisateur.getMdp());
		
		utilisateur.setMdp(mdpCrypte);

		
		utilisateurRepository.save(utilisateur);
		
		return "redirect:/listeUtilisateur";
	}
	
	@GetMapping("/listeUtilisateur")
	public String listeUtilisateur()
	{
		
		return "listeUtilisateur";
	}

}
