package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

public interface NoticeDao {
	
	// 공지사항의 갯수 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	public List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;
	
	public int insert(NoticeVO n) throws ClassNotFoundException, SQLException;
	
} // interface
