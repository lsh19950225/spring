package org.doit.ik.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// 공지사항과 관련된 모든 컨트롤러 메서드를 만드는 컨트롤러
@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	@Autowired // (required = false)
	private NoticeDao noticeDao;
	
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(Model model
			, @RequestParam(value="seq") String seq) throws ClassNotFoundException, SQLException {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		return "noticeDetail.jsp";
	}
	
	/*
	// 공지사항 목록 요청 URL
		// http://localhost/customer/noticeDetail.htm?seq=1
		@Override
		public ModelAndView handleRequest(HttpServletRequest request
				, HttpServletResponse response) throws Exception {
			
			// 리턴자료형 : ModelAndView p282
			ModelAndView mav = new ModelAndView("noticeDetail.jsp");
			
			  String seq = request.getParameter("seq");
			  
			  NoticeVO notice = this.noticeDao.getNotice(seq);
		      
		      mav.addObject("notice", notice);
			
			return mav;
		}
		*/
	
	// 공지사항 목록 요청 url
	// http://localhost/customer/notice.htm?page=2&field=검색조건&query=검색어 : 우리가 정함 : 약속
	@GetMapping("/notice.htm")
	public String notices (Model model
			// 기본값 설정 방법 : @RequestParam(value = "page", defaultValue = "1") int page
			, @RequestParam(value = "page", defaultValue = "1") int page
			, @RequestParam(value = "field", defaultValue = "title") String field
			, @RequestParam(value = "query", defaultValue = "") String query) throws ClassNotFoundException, SQLException {
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		model.addAttribute("list", list);
		model.addAttribute("message", "Hello world!");
		return "notice.jsp";
	}
	
	/* NoticeController.java 코딩
	public ModelAndView handleRequest(HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		// 리턴자료형 : ModelAndView p282
		ModelAndView mav = new ModelAndView();
		
		  String ppage = request.getParameter("page");
	      String pfield = request.getParameter("field");
	      String pquery = request.getParameter("query");
	      
	      int page = 1;
	      String field = "title";
	      String query = "";
	      
	      if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
	      if( pfield != null && !pfield.equals("") ) field = pfield;
	      if( pquery != null && !pquery.equals("") ) query = pquery;
	      
	      List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
	      
	      mav.addObject("list", list);
	      mav.addObject("message", "Hello World!");
	      
	      mav.setViewName("notice.jsp");
		
		return mav;
	}
	*/
	
} // class
