package org.doit.ik.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	String getTime(); // 디비에서 현재 시간을 읽어온다.
	
	@Select("SELECT SYSDATE+1 FROM dual")
	String getNextTime();
	
} // interface
