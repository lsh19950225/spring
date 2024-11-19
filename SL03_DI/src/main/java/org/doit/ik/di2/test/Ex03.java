package org.doit.ik.di2.test;



import org.doit.ik.di.RecordViewImpl;
import org.doit.ik.di2.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex03 {
	
	public static void main(String[] args) {
		
		// ApplicationContext ctx p60
		// 자바 코드일 때는
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class); // 네임 속성을 줄때
		// RecordViewImpl rvi = ctx.getBean("getRecordViewImpl", RecordViewImpl.class); // 안줄때
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
	} // main
	
} // class
