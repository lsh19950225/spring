package org.doit.ik.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class Message { // NoticeVO
	
	private String output;
	
	// name="attach" 와 똑같아야 된다.
	private CommonsMultipartFile attach;
	
} // class
