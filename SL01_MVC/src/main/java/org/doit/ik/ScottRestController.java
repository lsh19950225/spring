package org.doit.ik;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;

// Ajax 요청을 처리하는 컨트롤러
@RestController
public class ScottRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScottRestController.class);
	
	@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;
	
	// json -> DeptDTO 로 바로 받을 수 있다.
	@PostMapping(value = "/scott/dept/new")
	// ResponseEntity : 성공, 실패와 응답 상태값을 준다. / 네트워크에서 확인
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dto){
		logger.info("scottRestController.insertDept()");
		
		int rowCount = this.deptMapper.insertDept(dto);
		
		return rowCount == 1 
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// json -> DeptDTO 로 바로 받을 수 있다.
	@PostMapping(value = "/scott/dept/remove")
	// ResponseEntity : 성공, 실패와 응답 상태값을 준다. / 네트워크에서 확인
	public ResponseEntity<String> removeDept(@RequestBody int deptno){
		logger.info("scottRestController.removeDept()");
		
		int rowCount = this.deptMapper.removeDept(deptno);
		
		return rowCount == 1 
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
} // class
