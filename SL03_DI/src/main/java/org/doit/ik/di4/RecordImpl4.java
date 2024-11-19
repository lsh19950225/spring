package org.doit.ik.di4;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Component(value = "record4") // 이름 안주면 클래스 이름의 앞 소문자
public class RecordImpl4 implements Record4 {
	
	private int kor;
	private int eng;
	private int mat;
	
	@Override
	public int total() {
		return this.kor + this.eng + this.mat;
	}
	@Override
	public double avg() {
		return total()/3.0;
	}
	
} // class
