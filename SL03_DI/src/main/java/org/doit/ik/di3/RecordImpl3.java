package org.doit.ik.di3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordImpl3 implements Record3 {
	
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
