package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	@GetMapping("/login")
	public String viewLoginForm() {
		
		return "login";
	}
	@GetMapping("/logout")
	public String viewWelcomeForm() {
		
		return "login";
	}
	
	@GetMapping("/signup")
	public String viewSignUp() {
		
		return "signUp";
	}
}
