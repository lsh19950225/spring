package org.doit.ik.di2;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// application-context.xml == 자바 클래스 설정 파일 p66
// p85 자바 코드로 스프링 설정
@Configuration // 스프링 설정을 나타내는 클래스
public class Config {
	
	/* <bean id="record" class="org.doit.ik.di.RecordImpl"></bean> */
	// 위와 똑같은 코딩 / 식별자의 이름이 메서드의 이름이 되어야 된다.
	// 리턴 타입
	@Bean
	public RecordImpl record() {
		return new RecordImpl();
	} //
	
	   // <bean id="rvi" class="org.doit.ik.di.RecordViewImpl">
	   	// <property name="record">
	   		// <ref bean="record" />
	   	// </property>
	   // </bean>
	@Bean(name = "rvi")
	public RecordViewImpl getRecordViewImpl() {
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record());
		return rvi;
	} // 
	
} // class
