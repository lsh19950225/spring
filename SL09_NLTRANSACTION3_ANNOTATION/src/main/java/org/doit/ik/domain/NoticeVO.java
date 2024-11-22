package org.doit.ik.domain;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
	
	private String seq;
	private String title;
	private String writer;
	private Date regdate;
	private String filesrc; // 수정 / 디비에 따로 인서트 시키기 위한 파일명이다.
	private int hit;
	private String content;
	
	// 파일 객체를 받기 위함 / 부모 MultipartFile(이것도 가능하다.) / 자식인 CommonsMultipartFile 이걸로 한다.
	private CommonsMultipartFile file;
	
} // class
