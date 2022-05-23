package com.inti.SpringBootValidationThymeleaf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inti.SpringBootValidationThymeleaf.model.Role;
import com.inti.SpringBootValidationThymeleaf.model.Utilisateur;
import com.inti.SpringBootValidationThymeleaf.repository.RoleRepository;
import com.inti.SpringBootValidationThymeleaf.repository.UtilisateurRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{

		Utilisateur utilisateur = utilisateurRepository.findByUsernameOrEmail(username, username);
		
		if(utilisateur == null)
		{
			throw new UsernameNotFoundException("Le username " + username + " n'existe pas en BDD.");
		}
		
		return new User(utilisateur.getUsername(), utilisateur.getMdp(), getListeGrantedAuthority(username));
	}
	
	private List<GrantedAuthority> getListeGrantedAuthority(String username)
	{
		List<GrantedAuthority> listeGrantedAuthorities = new ArrayList<GrantedAuthority>();

		List<Role> lisRoles = roleRepository.findRolesByUsername(username);
		
		for (Role role : lisRoles)
		{
			listeGrantedAuthorities.add(new SimpleGrantedAuthority(role.getNomRole()));
		}
		
		return listeGrantedAuthorities;
	}

}
