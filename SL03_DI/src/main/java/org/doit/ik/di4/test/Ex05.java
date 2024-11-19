package org.doit.ik.di4.test;

import org.doit.ik.di4.RecordViewImpl4;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {
	
	public static void main(String[] args) {
		
		// [자동 의존성 주입] p103
		
		// 경로
		String resourceLocations = "classpath:org/doit/ik/di4/application-context4.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		// 빈으로 받아준다.
		// RecordViewImpl.class 있으면 다운캐스팅 안해도 된다.
		// 없으면 오브젝트라 다운캐스팅 해줘야 된다.
		RecordViewImpl4 rvi = ctx.getBean("recordViewImpl4", RecordViewImpl4.class);
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
	} // main
	
} // class
