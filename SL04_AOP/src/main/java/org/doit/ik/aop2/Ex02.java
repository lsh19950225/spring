package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {
	
	public static void main(String[] args) {
		// p204 스프링 AOP : 아직 사용안한 단계
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:org/doit/ik/aop2/application-context2.xml");
		
		Calculator calc = ctx.getBean("calcProxy", Calculator.class);
		// Calculator calc = ctx.getBean("calc", Calculator.class); // 이러면 핵심기능만 된다.
		System.out.println(calc.add(3, 5)); // 처리시간(공통기능)
		
		System.out.println("END");
	} // main
	
} // class
