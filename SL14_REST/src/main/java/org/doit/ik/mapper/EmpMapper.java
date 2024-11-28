package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.EmpVO;

public interface EmpMapper {
   
   List<EmpVO> selectAll(); // 모든 사원 정보 반환
   
   EmpVO selectByEmpno(int empno); // 해당 사원 번호 정보 반환
   
   Integer idCheck(String empno); // ID 중복 체크
   
} // interface