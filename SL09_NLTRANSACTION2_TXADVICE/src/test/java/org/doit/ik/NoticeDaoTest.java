package org.doit.ik;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDaoImpl;
import org.junit.jupiter.api.Test;

class NoticeDaoTest {
	
	@Test
	void testNoticeInsert() {
		// System.out.println("hello world");
		NoticeDaoImpl noticeDao = new NoticeDaoImpl();
		NoticeVO notice = new NoticeVO();
		notice.setTitle("두 번째 게시글");
		notice.setContent("두 번째 게시글");
		int rowCount = 0;
		try {
			rowCount = noticeDao.insert(notice);
			System.out.println(rowCount);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("공지사항 추가");
	}

} // class
