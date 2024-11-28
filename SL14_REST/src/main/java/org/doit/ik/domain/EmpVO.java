package org.doit.ik.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpVO {
	
	   private int empno;
	   private String ename;
	   private String job;
	   
	   @JsonIgnore  // JSON 결과에 제외시킨다.
	   private int mgr;
	   
	   @JsonFormat(pattern = "yyyyMMddHHmmss" ) // 형식 주는 법
	   private Date hiredate;
	   
	   private int sal;
	   private double comm;
	   private int deptno;
	
} // class
