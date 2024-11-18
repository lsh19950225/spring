package org.doit.ik.di.test;

import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {
	
	public static void main(String[] args) {
		
		// application-context.xml : 스프링 빈 객체를 생성 + DI 설정 파일
		// ApplicationContext = 공장 = 스프링 컨테이너
		// XmlWeb[ApplicationContext] p59
		
		// 경로
		String resourceLocations = "classpath:org/doit/ik/di/application-context.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		// 빈으로 받아준다.
		// RecordViewImpl.class 있으면 다운캐스팅 안해도 된다.
		// 없으면 오브젝트라 다운캐스팅 해줘야 된다.
		RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class);
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
	} // main
	
} // class
