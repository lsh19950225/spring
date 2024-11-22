package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException
	{
		String sql = " SELECT * "
				+ " FROM member "
				+ " WHERE id=? ";
		
		return this.jdbcTemplate.queryForObject(sql, MemberVO.class, id);
	}
	
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException
	{
		String sql = "INSERT INTO MEMBER(id, pwd, name, gender, birth, is_lunar, cphone, email, habit, regdate) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		return this.jdbcTemplate.update(sql, member.getId(), member.getPwd(), member.getName(), member.getGender(), member.getBirth(), member.getIs_lunar(), member.getCphone()
				, member.getEmail(), member.getHabit());
	}
}
