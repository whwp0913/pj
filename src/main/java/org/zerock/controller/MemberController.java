package org.zerock.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberVO;
import org.zerock.security.UserDetailsServiceImpl;
import org.zerock.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="error", required=false)String error,
					@RequestParam(value="logout", required = false)String logout) {
		
		ModelAndView model = new ModelAndView();

		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}

		model.setViewName("login");
		return model;
	}
	
	@GetMapping("/signup")
	public void signUp() {

	}

	@PostMapping("/signup")
	public void signUpPost(MemberVO vo, Model model, RedirectAttributes rttr, HttpSession session,
			HttpServletResponse res) throws Exception {
		
		vo.setMpw(UserDetailsServiceImpl.encoder(vo.getMpw()));
		service.userRegist(vo);
		service.insertAuth(vo.getMid());
		
		
		service.getUserID(vo.getMid());
		session.setAttribute("LOGIN", vo);
		Object uri = session.getAttribute("URI");

		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		out.println(uri != null ? "<script>alert('회원가입되었습니다'); location.href='" + (String) uri + "' </script>"
				: "<script>alert('회원가입되었습니다'); location.href='/login' </script>");
		
	}

	@PostMapping("/idCheck")
	public ResponseEntity<String> checkId(@RequestBody String id) {
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<String>(Integer.toString(service.userIdCheck(id)), HttpStatus.OK);	
		return entity;
	}

}
