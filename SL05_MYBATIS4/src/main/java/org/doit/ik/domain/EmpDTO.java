package org.doit.ik.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDTO {
	
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	
	// 문자열을 날짜 객체로 변환
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date hiredate; // java.util.Date
	
	private int sal;
	private double comm;
	private int deptno;
	
	// empDTO + salgradeDTO 1:1 관계
	private SalgradeDTO salgradeDTO;
	
} // class
