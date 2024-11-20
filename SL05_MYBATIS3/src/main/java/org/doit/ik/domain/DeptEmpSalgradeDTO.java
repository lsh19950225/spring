package org.doit.ik.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("DeptEmpSalgradeDTO")
public class DeptEmpSalgradeDTO {
	
	// 1:1 관계(연관 관계)
	private DeptDTO deptDTO;
	
	// 1:N 관계(연관 관계)
	private EmpDTO empDTO; // List<EmpDTO> empList
	
} // class
