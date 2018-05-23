package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@RequestMapping("")
@Controller
@Log4j
public class LoginController {

	@GetMapping("/login")
	public void login() {
		
		log.info("Login get............................");
	}
}
