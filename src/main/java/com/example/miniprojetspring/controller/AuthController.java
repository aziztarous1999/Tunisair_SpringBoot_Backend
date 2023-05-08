package com.example.miniprojetspring.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniprojetspring.Dto.AuthResponseDTO;
import com.example.miniprojetspring.Dto.LoginDto;
import com.example.miniprojetspring.Dto.RegisterDto;
import com.example.miniprojetspring.entities.Role;
import com.example.miniprojetspring.entities.User;
import com.example.miniprojetspring.repository.RoleRepository;
import com.example.miniprojetspring.repository.UserRepository;
import com.example.miniprojetspring.security.JWTGenerator;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	private AuthenticationManager authManager;
	private UserRepository userRepository;
	private RoleRepository roleRepos;
	private PasswordEncoder passsEncod;
	private JWTGenerator jwtGenerator;
	
	@Autowired
	public AuthController(AuthenticationManager authManager, UserRepository userRepository, RoleRepository roleRepos,
			PasswordEncoder passsEncod, JWTGenerator jwtGenerator) {
		super();
		this.authManager = authManager;
		this.userRepository = userRepository;
		this.roleRepos = roleRepos;
		this.passsEncod = passsEncod;
		this.jwtGenerator = jwtGenerator;
	}
	
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<AuthResponseDTO>(new AuthResponseDTO(token), HttpStatus.OK);
    }
	
	
	
	@PostMapping("register")
	public ResponseEntity<String> register(
			@RequestBody RegisterDto registerDto){
		if(userRepository.existsByUsername(registerDto.getUsername())) {
			return new ResponseEntity<>("Username is taken!",HttpStatus.BAD_REQUEST);
		}
			
		User user = new User();
		user.setUsername(registerDto.getUsername());
		user.setPassword(passsEncod.encode(registerDto.getPassword()));
		
		Role roles = roleRepos.findByName("USER").get();
		user.setRoles(Collections.singletonList(roles));
		
		userRepository.save(user);
		
		return new ResponseEntity<>("User Register Success!",HttpStatus.OK);
	}
	

}
