package org.doit.ik.aop3;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex03 {
	
	public static void main(String[] args) {
		// p209 XML 스키마 기반 AOP 처리
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:org/doit/ik/aop3/application-context3.xml");
		
		Calculator calc = ctx.getBean("calc3", Calculator.class);
		// Calculator calc = ctx.getBean("calc", Calculator.class); // 이러면 핵심기능만 된다.
		System.out.println(calc.add(3, 5)); // 처리시간(공통기능)
		
		System.out.println("END");
	} // main
	
} // class
