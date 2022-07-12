package kr.ac.kopo.kopo18.spring.scoreadmin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity //==>Database Table로 취급
public class ScoreItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto_increment 추가 (GenerationType.IDENTITY)
	@Column
	private Long id;				// 용도 : 시스템에서 사용하는 구분자
//	@Column
//	private String name;
//	@Column
//	private int studentid;			// 용도 : 사용자에게 보여주는 구분
	@Column
	private String test;
	@Column
	private int kor;
	@Column
	private int eng;
	@Column
	private int mat;
	
	// ########## 2022.06.29 추가 ##########
	@ManyToOne(optional=false)
	@JoinColumn(name="score_list_id")	// DB column 이름 (생략 가능)
	private ScoreList scoreList;
	
//	@Override
//	public String toString() {
//		String result = "[score_"+id+"] 국어 : "+kor+" 영어 : "+eng+" 수학 : "+mat; 
//		return result;
//	}
	
	// ####################################

	// 기본 생성자
	public ScoreItem() {};
	
	// Generate Getters & Setters
	public ScoreList getScoreList() {
		return scoreList;
	}

	public void setScoreList(ScoreList scoreList) {
		this.scoreList = scoreList;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
	
	
	// * Entity Class에는 Setter가 존재하면 안 됨
	// -> Entity Class = Table 그 자체 -> 각각의 멤버변수가 해당 테이블의 칼럼
	// -> 칼럼에 대한 setter를 무작정 생성하는 경우, 객체의 값이 어느 시점에 변경되었는지 알 수가 없음.
	

}
