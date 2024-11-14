package org.doit.ik.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
	
	   private int empno;
	   private String ename;
	   private String job;
	   private int mgr;
	   private Date hiredate;
	   private int sal;
	   private double comm;
	   private int deptno;
	
} // class
