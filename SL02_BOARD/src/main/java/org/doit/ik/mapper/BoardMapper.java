package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.doit.ik.domain.BoardVO;

public interface BoardMapper {
	
	// List 확장성이 더 있어서 쓴다.
	List<BoardVO> getList();
	
	void insert(BoardVO board); // 새글 쓰기
	void insertSelectKey(BoardVO board); // 새글 쓰기 + 글번호(pk) 반환
	
	BoardVO read(Long bno); // 게시글 상세보기
	
	int update(BoardVO board); // 게시글 수정
	
	int delete(Long bno); // 게시글 삭제
	
} // interface
