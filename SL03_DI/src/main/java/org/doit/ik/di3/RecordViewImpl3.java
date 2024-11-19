package org.doit.ik.di3;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

@Setter
public class RecordViewImpl3 implements RecordView3 {
	
	// @Setter... 롬복 어노테이션도 가능하다.
	// @Autowired(required = false) // 자동으로 주입이 됨
	// @Autowired
	// @Resource(name = "record1") : 지금 버전으로는 못쓴다. 의미는 알고가자 여러개 있을 때 쓰면 된다.
	@Inject // 리소스 못쓸때는 이렇게 쓰면된다.
	@Named(value = "record1")
	private RecordImpl3 record = null;
	// 객체 생성 안하면 널포인트이셉션 오류 뜬다. / 결합력이 높아 좋지 않은 코딩이다.
	// private RecordImpl record = new RecordImpl();
	
	public RecordViewImpl3() {
		
	}
	
	// 생성자를 통해서 주입한 것
	public RecordViewImpl3(RecordImpl3 record) {
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
