package org.doit.ik.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	
	// member 테이블의 컬럼명과 필드명 동일
	private String id; // 1. 수정 uid -> id
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar; // 2. 수정
	private String cphone; // 3. 수정
	private String email;
	private String habit;
	private Date regdate; // 4. 수정
	
	private int point; // 추가
	
	// 추가된 필드
	private boolean enabled;
	private List<AuthVO> authList;
	
} // class
