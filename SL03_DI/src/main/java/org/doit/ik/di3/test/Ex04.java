package org.doit.ik.di3.test;

import org.doit.ik.di.RecordViewImpl;
import org.doit.ik.di3.RecordViewImpl3;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {
	
	public static void main(String[] args) {
		
		// [자동 의존성 주입] p103
		
		// 경로
		String resourceLocations = "classpath:org/doit/ik/di3/application-context3.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		// 빈으로 받아준다.
		// RecordViewImpl.class 있으면 다운캐스팅 안해도 된다.
		// 없으면 오브젝트라 다운캐스팅 해줘야 된다.
		RecordViewImpl3 rvi = ctx.getBean("rvi", RecordViewImpl3.class);
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
	} // main
	
} // class
