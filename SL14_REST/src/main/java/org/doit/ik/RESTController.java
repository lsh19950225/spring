package org.doit.ik;

import java.util.List;

import org.doit.ik.domain.EmpVO;
import org.doit.ik.domain.Ticket;
import org.doit.ik.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController // ajax가 필요한 컨트롤러는 @RestController 이 있어야 된다.
@Log4j
@RequestMapping("/scott/*")
public class RESTController {
	
	@GetMapping("/getText")
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return "헬로우 월드!"; // 문자열을 응답한다.
	} // getText
	
	@GetMapping( value = "/getText2", produces = "text/plan; charset=UTF-8" ) // 한글이 깨지지 않는다.
	public String getText2() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return "헬로우 월드!"; // 문자열을 응답한다.
	} // getText2
	
	//
	@Autowired
	private EmpMapper empMapper;
	
	// @GetMapping( value = "/employees")
	@GetMapping(value = "/employees", produces = {
	         MediaType.APPLICATION_JSON_UTF8_VALUE
	         , MediaType.APPLICATION_XML_VALUE
	   } )
	public List<EmpVO> getEmpList() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return this.empMapper.selectAll();
	} // getEmpList
	
	// @GetMapping( value = "/employees")
	/*
	@GetMapping(value = "/employee/{empno}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE
			, MediaType.APPLICATION_XML_VALUE
	} )
	public EmpVO selectByEmpno(@PathVariable("empno") int empno) {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return this.empMapper.selectByEmpno(empno);
	} // selectByEmpno
	*/
	
	 
		// REST 방식 호출 -> 뷰 x, 데이터(String, xml, json) 응답
											// + 정상적인 데이터/비정상적인 데이터
		// ResponseEntity 객체 = 응답 데이터 + 상태 메세지
		@GetMapping(value = "/employee/{empno}", produces = {
				MediaType.APPLICATION_JSON_UTF8_VALUE
				, MediaType.APPLICATION_XML_VALUE
		} )
		public ResponseEntity<EmpVO> selectByEmpno(@PathVariable("empno") int empno) {
			log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
			EmpVO vo = this.empMapper.selectByEmpno(empno);
			if (vo == null) {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(vo);
			} // if else
		} // selectByEmpno
	
		@PostMapping(value = "/idCheck/{empno}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
		public int idCheck(@PathVariable("empno") String empno) {    
		      return this.empMapper.idCheck(empno);
		}
		
		@PostMapping(value = "/ticket", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
		public Ticket convert(@RequestBody Ticket ticket) { // json 객체를 바로 VO에 받는다.
		      ticket.setOwner( ticket.getOwner() + "님" );
		      log.info(">> "+ ticket);
		      return ticket;
		}
		
} // class
