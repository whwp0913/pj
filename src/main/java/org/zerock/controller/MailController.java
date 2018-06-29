package org.zerock.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MailController {
 
  @Autowired
  private JavaMailSender mailSender;
 
 
  // mailForm
  @GetMapping("/mailForm")
  public void mailForm() {
 
  }  
 
  // mailSending 코드
  @PostMapping("/mailSending")
  public String mailSending(HttpServletRequest request) {
   
    String setfrom = "choi2012341050@gmail.com";         
    String tomail  = "choi2012341050@gmail.com";     // 받는 사람 이메일
    String title   = request.getParameter("title");      // 제목
    String content = request.getParameter("content");    // 내용
   
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper 
                        = new MimeMessageHelper(message, true, "UTF-8");
 
      messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
      messageHelper.setTo(tomail);     // 받는사람 이메일
      messageHelper.setSubject(title+"님의 문의 메일"); // 메일제목은 생략이 가능하다
      messageHelper.setText(content);  // 메일 내용
     
      mailSender.send(message);
      
      System.out.println(setfrom);
      System.out.println(tomail);
      System.out.println(title);
      System.out.println(content);
      
    } catch(Exception e){
      System.out.println(e);
    }
   
    return "redirect:/mailForm";
  }
} 