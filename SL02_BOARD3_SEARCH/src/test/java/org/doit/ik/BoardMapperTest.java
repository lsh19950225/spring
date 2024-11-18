package org.doit.ik;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.mapper.BoardMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class BoardMapperTest {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	void testPaging() {
		Criteria criteria = new Criteria();
		List<BoardVO> list = 
				this.boardMapper.getListWithPaging(criteria);
		
		list.forEach(board->System.out.println(board));
	}

} // class
