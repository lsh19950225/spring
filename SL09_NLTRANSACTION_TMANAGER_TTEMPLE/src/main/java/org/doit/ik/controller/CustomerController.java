package org.doit.ik.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

// 공지사항과 관련된 모든 컨트롤러 메서드를 만드는 컨트롤러
@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	@Autowired // (required = false)
	private NoticeDaoImpl noticeDao;
	
	// <a href="download.htm?dir=customer/upload&file=${ notice.filesrc }">${ notice.filesrc }</a>
	// 컨트롤러 메서드 선언
	   // download.htm
	   // ?
	   // dir=customer/upload    경로
	   //&
	   //file=다운로드할 파일명
	   @RequestMapping("download.htm")
	   public void download (
	           HttpServletResponse response
	           , HttpServletRequest request
	           , @RequestParam("dir") String p
	           , @RequestParam("file") String f
	         ) throws Exception{ 
	      
	      String fname =  f;  
	      response.setHeader("Content-Disposition","attachment;filename="+ new String(fname.getBytes(), "ISO8859_1"));      
	       
	      String fullPath = request.getServletContext().getRealPath(   p + "/" + fname);
	    
	      FileInputStream fin = new FileInputStream(fullPath);
	      ServletOutputStream sout = response.getOutputStream(); // 응답 스트림
	      byte[] buf = new byte[1024];
	      int size = 0;
	      while((size = fin.read(buf, 0, 1024)) != -1) {
	         sout.write(buf, 0, size); 
	      }
	      fin.close();
	      sout.close();
	      
	   } // download
	
	// 게시글 삭제
	@GetMapping("noticeDel.htm")
	public String noticeDelete (@RequestParam("seq") String seq
			, @RequestParam("filesrc") String filesrc
			, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// 1. 첨부파일이 있는 공지사항이라면 첨부파일도 폴더에서 삭제한다.
		// if (filesrc != null) // x
		// if (filesrc.equals("")) // o
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		File delFile = new File(uploadRealPath, filesrc);
		if (delFile.exists() && delFile.isFile()) {
			delFile.delete();
		} // if
		
		// 2.
		int rowCount = this.noticeDao.delete(seq);
		if (rowCount == 1) {
			// 글목록 페이지 이동
			return "redirect:notice.htm";
		} else {
			// 글쓰기 페이지 이동
			return "redirect:noticeDetail.htm?seq="+seq+"&error";
		} // if else
	} //
	
	// <!-- http://localhost/customer/noticeEdit.htm?seq=6 -->
	// <input type="submit" value="수정" class="btn-save button" />
	@PostMapping("noticeEdit.htm")
	public String noticeEdit (NoticeVO notice, Model model
			, @RequestParam("o_filesrc") String oFilesrc, HttpServletRequest request) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
				
				// 1.
				CommonsMultipartFile multipartFile = notice.getFile();
				String uploadRealPath = null;
				// 없으면 true 반환한다.
				if ( !multipartFile.isEmpty() ) {
					// 실제 배포된 경로
					uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
					System.out.println("uploadRealPath : " + uploadRealPath);
					// 이전 원래 첨부 파일 삭제
					File delFile = new File(uploadRealPath, oFilesrc);
					if (delFile.exists() && delFile.isFile()) {
						delFile.delete();
					} // if
					
					// 새로운 첨부 파일 저장
					String originalFilename = multipartFile.getOriginalFilename();
					String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
					
					File dest = new File(uploadRealPath, filesystemName);
					multipartFile.transferTo(dest); // 실제 파일 저장
					
					notice.setFilesrc(filesystemName); // 디비에 반영
				} else { // 첨부 파일이 없는 경우
					notice.setFilesrc(oFilesrc);
				} // if else
		
		// 2.
		int rowCount = this.noticeDao.update(notice);
		if (rowCount == 1) {
			// 글목록 페이지 이동
			return "redirect:noticeDetail.htm?seq=" + notice.getSeq();			
		} else {
			// 글쓰기 페이지 이동 포워딩
			return "redirect:notice.htm";
		} // if else
	} //
	
	// <a class="btn-edit button" href="noticeEdit.htm?seq=${ notice.seq }">수정</a>
	@GetMapping("noticeEdit.htm")
	public String noticeEdit (@RequestParam("seq") String seq, Model model) throws ClassNotFoundException, SQLException {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		return "noticeEdit.jsp";
	} //
	
	// 															/customer/upload			a.txt
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
	      int index = 1;      
	      while( true ) {         
	         File f = new File(uploadRealPath, originalFilename);         
	         if( !f.exists() ) return originalFilename;         
	         // upload 폴더에 originalFilename 파일이 존재한다는 의미         a     .txt (4자리)
	         String fileName = originalFilename.substring(0, originalFilename.length() - 4 );  //   a
	         String ext =  originalFilename.substring(originalFilename.length() - 4 );  // .txt
	         // a-2.txt
	         originalFilename = fileName+"-"+(index)+ext;
	         
	         index++;
	      } // while 
	   }
	
	// <input class="btn-save button" type="submit" value="저장" />
	// <form action="" method="post">
	// 커맨드객체 : get set 만들고 파라미터 이름과 VO 이름과 똑같아야된다.
	@PostMapping(value="/noticeReg.htm")
	public String noticeReg (NoticeVO notice, HttpServletRequest request) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		// 1.
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = null;
		// 없으면 true 반환한다.
		if ( !multipartFile.isEmpty() ) {
			// 실제 배포된 경로
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("uploadRealPath : " + uploadRealPath);
			
			//			a.txt 있으면 a_1.txt ...
			String originalFilename = multipartFile.getOriginalFilename(); // 실제 파일 이름
			
			// 실제 저장될 파일 이름
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			
			File dest = new File(uploadRealPath, filesystemName);
			multipartFile.transferTo(dest); // 실제 파일 저장
			
			notice.setFilesrc(filesystemName); // 디비에 반영
		} // if
		
		notice.setWriter("lsh");
		// 2.
		// int rowCount = this.noticeDao.insert(notice);
		
		int rowCount = 1;
		this.noticeDao.insertAndPointUpOfMember(notice, "lsh");
		if (rowCount == 1) {
			// 글목록 페이지 이동
			return "redirect:notice.htm";			
		} else {
			// 글쓰기 페이지 이동 포워딩
			return "noticeReg.htm";
		} // if else
	} //
	
	// <a class="btn-write button" href="noticeReg.htm">글쓰기</a>
	@GetMapping("/noticeReg.htm")
	public String noticeReg (HttpSession session) {
		// session."auth"
		// 스프링 시큐리티(보안) : 인증 + 권한 처리
		return "noticeReg.jsp";
	} //
	
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
