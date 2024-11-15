package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;

public interface BoardService {
	
	List<BoardVO> getList();
	
	void register(BoardVO board);
	
	BoardVO get(Long bno);
	
	boolean modify(BoardVO board);
	
	boolean delete(Long bno);
	
} // interface
