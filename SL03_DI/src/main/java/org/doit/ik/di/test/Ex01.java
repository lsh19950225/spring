package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {
	
	public static void main(String[] args) {
		
		RecordImpl record = new RecordImpl();
		
		// 1. 생성자 의존성 주입(DI)
		// RecordViewImpl rvi = new RecordViewImpl(record);
		
		// 2. Setter 의존성 주입(DI)
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record);
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
	} // main
	
} // class
