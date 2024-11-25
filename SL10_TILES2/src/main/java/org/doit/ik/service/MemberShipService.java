package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;

public interface MemberShipService {
	
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException;
	
} // interface
