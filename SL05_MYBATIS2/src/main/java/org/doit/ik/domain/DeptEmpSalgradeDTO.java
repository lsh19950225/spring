package org.doit.ik.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptEmpSalgradeDTO {
	
	   private int deptno;
	   private String dname;
	   private int empno;
	   private String ename;
	   private String job;
	   private Date hiredate;
	   private double sal;
	   private int grade;
	
} // class
