package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 공지사항 목록 컨트롤러
public class NoticeDetailController implements Controller {
	
	private NoticeDaoImpl noticeDao;
	
	public NoticeDetailController() {
		
	}
	
	// 생성자 DI
	public NoticeDetailController(NoticeDaoImpl noticeDao) {
		super();
		this.noticeDao = noticeDao;
	}
	
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
	
} // class
