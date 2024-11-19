package org.doit.ik.aop4;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {
	
	public static void main(String[] args) {
		// p226 @Aspect 어노테이션을 사용한 AOP 처리
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:org/doit/ik/aop4/application-context4.xml");
		
		Calculator calc = ctx.getBean("calc4", Calculator.class);
		// Calculator calc = ctx.getBean("calc", Calculator.class); // 이러면 핵심기능만 된다.
		System.out.println(calc.add(3, 5)); // 처리시간(공통기능)
		
		System.out.println("END");
	} // main
	
} // class
