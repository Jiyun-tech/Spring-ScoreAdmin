package kr.ac.kopo.kopo18.spring.scoreadmin.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreItem;
import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreList;

// Test 1. ScoreList 가져와서 ScoreItem 확인
// Test 2. ScoreItem 가져와서 ScoreList 확인 (FetchType LAZY 모드 & EAGER 모드 2가지)
// Test 3. Delete

@RunWith(SpringRunner.class)
@SpringBootTest
class ScoreListRepositoryTest {

	@Autowired
	private ScoreListRepository scoreListRepository;
	
	@Autowired
	private ScoreItemRepository scoreItemRepository;
	
//	// 부모 및 자식 클래스 생성
//	@Test
////	@Transactional
//	public void oneToMany_TwoWay() {
//		// ScoreList first = scoreListRepository.findOneById(2L);
//		
//////		for (int i = 2; i < 100; i++) {
//////		scoreListRepository.saveNew("폴리"+i);
//////		}
////		
//////		List<ScoreList> list = scoreListRepository.findAll();
//////		
//////		for ( ScoreList s : list) {
//////			System.out.println(s.toString());
//////		}
//		
//		scoreListRepository.deleteAll();
//	}
	
//	// Fetch Test : Find ScoreList from ScoreItem
//	// LAZY & EAGER -> all succeeded
//	@Test
//	void scoreItemTest() {
//		ScoreItem scoreItem = scoreItemRepository.findOneById(57l);
//		System.out.println("외래키 : " + scoreItem.getScoreList());		
//	}
	
//	 Fetch Test : Find ScoreItem from ScoreList
//	 LAZY -> failed / EAGER -> succeeded
	@Test
	void scoreListTest() {
		ScoreList scoreList = scoreListRepository.findOneById(12l);
		/*
		 * <LAZY MODE>
		 * Hibernate: select scorelist0_.id as id1_1_, 
		 * 					scorelist0_.name as name2_1_,
		 * 					scorelist0_.studentid as studenti3_1_ 
		 * 				from score_list scorelist0_ 
		 * 				where scorelist0_.id=? 
		 * <EAGER MODE>
		 * Hibernate: select scoreitems0_.score_list_id as score_li5_0_0_, 
		 * 					scoreitems0_.id as id1_0_0_, scoreitems0_.id as id1_0_1_,
		 * 					scoreitems0_.eng as eng2_0_1_, scoreitems0_.kor as kor3_0_1_, scoreitems0_.mat as mat4_0_1_, 
		 * 					scoreitems0_.score_list_id as score_li5_0_1_
		 * 				from score_item scoreitems0_ 
		 * 				where scoreitems0_.score_list_id=?
		 */
		List<ScoreItem> scoreItems = (List<ScoreItem>) scoreList.getScoreItems();
//		// scoreItem 없이 조회 -> LAZY, EAGER 상관 없이 성공
		System.out.println("\nID : " + scoreList.getId() 
							+" / STUDENT ID : " + scoreList.getStudentid()
							+ " / NAME : " + scoreList.getName()
							);
		
		// scoreItem 조회 -> LAZY 모드 실패
		for (int i = 0 ;i < scoreItems.size(); i++ ) {
		System.out.println("\nID : " + scoreList.getId() 
							+" / STUDENT ID : " + scoreList.getStudentid()
							+ " / NAME : " + scoreList.getName()
							+ " / ScoreItmes ID : " + scoreItems.get(i).getId()
							);
		}

	}
		
//	// Delete
//	@Test
//	@Transactional
//	void deleteScoreItem() {
//		scoreItemRepository.deleteById(1L);
//		
//		List<ScoreItem> scoreItem = scoreItemRepository.findAllById(1L);
//		assertEquals(0, scoreItem.size());
//		
//		// 콘솔 출력으로 확인 --> 부모 클래스 전체 출력하여 자식 클래스 삭제됐는지 체크
//		ScoreList scoreList = scoreListRepository.findOneById(1L);
//		System.out.println("*****socreList findOneByID(1L)*****");
//		System.out.println("ScoreList ID : " + scoreList.getId()
//							+"\nScoreItem : " + scoreList.getScoreItems());
//	}
//	
//	// Delete
//	@Test
//	@Transactional
//	void deleteScoreList() {
//		scoreListRepository.deleteById(2l);
//		
//		List<ScoreItem> list2 = scoreItemRepository.findAllById(2l);
//		assertEquals(0, list2.size());
//		
//		// 콘솔 출력으로 확인 --> 부모 클래스 전체 출력하여 해당하는 id 삭제되었는지 확인
//		List<ScoreList> list = scoreListRepository.findAll();
//		System.out.println("*****socreList findAll*****");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("[부모ID : "+list.get(i).getId()+"] "+list.get(i).getName()+" "+list.get(i).getScoreItems()+"\n");
//		}
//		
//		// 콘솔 출력으로 확인 
//		// --> 자식 클래스 전체 출력하여, 삭제된 부모 클래스를 foreign key로 갖고 있던 자식 클래스도 삭제되었는지 확인
//		List<ScoreItem> item = scoreItemRepository.findAll();
//		System.out.println("*****socreItem findAll*****");
//		for (int i = 0; i < item.size(); i++) {
//			System.out.println("[자식ID : "+item.get(i).getId()+"]" + item.get(i).getScoreList());
//		}
//		
//	}
	
}

