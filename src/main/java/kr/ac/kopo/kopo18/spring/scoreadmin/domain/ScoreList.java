package kr.ac.kopo.kopo18.spring.scoreadmin.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ScoreList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto_increment 추가 (GenerationType.IDENTITY)
	@Column
	private Long id;				// 용도 : 시스템에서 사용하는 구분자
	
	@Column
	private String name;
	
	@Column
	private int studentid;		// 용도 : 사용자에게 보여주는 구분
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="scoreList", orphanRemoval = true) // 멤버변수 이름
								// fetch=FetchType.EAGER or LAZY
	// LAZY : 지연 로딩 (부모 입장에서 자식 가져얼 때 기본 세팅). 1:多 관계이기 때문에 매번 로딩하기엔 부담이 됨.
	// EAGER : 즉시 로딩 (자식 입장에서 부모 가져올 때 기본 세팅). 자식은 하나의 부모에만 연결되어있기 때문에 부담 없음.
	@Column
	private Collection<ScoreItem> scoreItems;

	public ScoreList() {} 		// 기본 생성자
	
	public Collection<ScoreItem> getScoreItems() {
		if ( scoreItems == null) {
			scoreItems = new ArrayList<ScoreItem>();
		}
		return scoreItems;
	}

	public void setScoreItems(Collection<ScoreItem> scoreItems) {
		this.scoreItems = scoreItems;
	}

	public void addScoreItem(ScoreItem scoreItem) {
		Collection<ScoreItem> scoreItems = getScoreItems();
		scoreItems.add(scoreItem);
	}

//	@Override
//	public String toString() {
//		String result = "[" + id + "] " +studentid+" "+name;
//		for ( ScoreItem scoreItem : getScoreItems()) {
//			result += "\n" + scoreItem.toString();
//		}
//		return result;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	
}
