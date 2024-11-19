package org.doit.ik.di4;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
@Component
public class RecordViewImpl4 implements RecordView4 {
	
	@Inject // 리소스 못쓸때는 이렇게 쓰면된다.
	@Named(value = "record4")
	private RecordImpl4 record = null;
	// 객체 생성 안하면 널포인트이셉션 오류 뜬다. / 결합력이 높아 좋지 않은 코딩이다.
	// private RecordImpl record = new RecordImpl();
	
	public RecordViewImpl4() {
		
	}
	
	// 생성자를 통해서 주입한 것
	public RecordViewImpl4(RecordImpl4 record) {
		this.record = record;
	}

	@Override
	public void input() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("> 국, 영, 수 입력 ?");
			int kor = scanner.nextInt();
			int eng = scanner.nextInt();
			int mat = scanner.nextInt();
			
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
		} catch (Exception e) {
			e.printStackTrace();
		} // try catch
	}
	
	@Override
	public void output() {
		System.out.printf("kor=%d, eng=%d, mat=%d, tot=%d, avg=%.2f\n"
				, this.record.getKor()
				, this.record.getEng()
				, this.record.getMat()
				, this.record.total()
				, this.record.avg()
				);
	}
	
} // class
