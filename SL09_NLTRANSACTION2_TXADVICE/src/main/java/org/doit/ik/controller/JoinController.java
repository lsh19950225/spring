package org.doit.ik.controller;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.persistence.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 공지사항 
@Controller
@RequestMapping("/joinus/*")
public class JoinController {

   @Autowired
   private MemberDao memberDao;
   
   // 로그인        /joinus/login.htm      ->    /joinus/login.jsp
   @GetMapping("/login.htm")
   public String login() throws Exception{
      return "login.jsp";
   }
   
   // X   스프링 시큐리티 사용해서 처리....
   
   // 회원 가입      /joinus/join.htm       ->     /joinus/join.jsp 응답.
   @GetMapping("/join.htm")
   public String join() throws Exception{
      return "join.jsp";
   }
   
   // 회원 가입      /joinus/join.htm   + POST    ->     메인 페이지 응답.
   @PostMapping("/join.htm")
   public String join( MemberVO member) throws Exception{
      this.memberDao.insert(member);
      return "redirect:../index.htm";   //   
   }
    
} // class






