package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardMapper {
	
	// List 확장성이 더 있어서 쓴다.
	List<BoardVO> getList(); // 페이징 처리 x
	List<BoardVO> getListWithPaging(Criteria criteria); // 페이징 처리 o
	int getTotalCount(Criteria criteria); // 총 레코드 수를 반환
	
	void insert(BoardVO board); // 새글 쓰기
	void insertSelectKey(BoardVO board); // 새글 쓰기 + 글번호(pk) 반환
	
	BoardVO read(Long bno); // 게시글 상세보기
	
	int update(BoardVO board); // 게시글 수정
	
	int delete(Long bno); // 게시글 삭제
	
} // interface
