package com.example.miniprojetspring.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.miniprojetspring.entities.Role;
import com.example.miniprojetspring.entities.User;
import com.example.miniprojetspring.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAthorities(user.getRoles()));
	}

	
	private Collection<GrantedAuthority> mapRolesToAthorities(List<Role> roles){
		return roles.stream().map( role -> new SimpleGrantedAuthority(role.getName()) ).collect(Collectors.toList());
	}
	
}
