package org.doit.ik.domain;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class MultiMessage { // NoticeVO
	
	private String output;
	// name="attach" 와 똑같아야 된다.
	// private CommonsMultipartFile [] attach; // 배열로 받을 수 있다.
	private List<CommonsMultipartFile> attach;
	
} // class
