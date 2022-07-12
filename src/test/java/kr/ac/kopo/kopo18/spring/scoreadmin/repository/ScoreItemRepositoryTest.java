package kr.ac.kopo.kopo18.spring.scoreadmin.repository;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreItem;

@SpringBootTest
@Transactional	// 각각의 test 독립적으로 수행 (각각의 Method 실행 후, 데이터 rollback)
class ScoreItemRepositoryTest {

	@Autowired
	private ScoreItemRepository scoreItemRepository;
	
//	@Test
//	void contextLoads() {
//		Map<String, Object> filter = new HashMap<String, Object>();
//		filter.put("kor", "20");
//		
//		PageRequest pageable = PageRequest.of(1, 5);
//		Page<ScoreItem> page = scoreItemRepository.findAll(ScoreItemSpecs.search(filter), pageable);
//		for (ScoreItem scoreItem : page) {
//			System.out.print("id : " + scoreItem.getId()+ " ");
//			System.out.print("kor : " + scoreItem.getKor()+ " ");	
//			System.out.println("eng : " + scoreItem.getEng() + " ");	
//			System.out.println("mat : " + scoreItem.getMat());	
//		}
//		
//		System.out.println("Page Size : " + page.getSize());
//		System.out.println("Total Page : " + page.getTotalPages());
//		System.out.println("Total Count : " + page.getTotalElements());
//		System.out.println("NEXT : " + page.nextPageable());
//		
//		List<ScoreItem> list = page.getContent();
//		for (int i = 0; i<list.size(); i++) {
//			System.out.println(list.get(i).getId());
//		}
//	}
	
//	@Test// page
//	void testPage() {
//		
//		PageRequest pageable = PageRequest.of(4, 16);
//		Page<ScoreItem> page = scoreItemRepository.findAll(pageable);
//		
//		System.out.println("Page Size : " + page.getSize());			// dataPerPage
//		System.out.println("Total Page : " + page.getTotalPages());		// nnPage (Last Page Number)
//		System.out.println("Total Count : " + page.getTotalElements());	// total data number
//		System.out.println("NEXT : " + page.nextPageable());			// next Page information
//		System.out.println("NEXT Page Num : " + page.nextPageable().getPageNumber());
//		System.out.println("getNumber : " + page.getNumber()); 			// currentP age Number (begin from 0)
//		System.out.println("getNumberOfElements : " + page.getNumberOfElements()); // data count of currentPage
//		
//	}
	
//	@Test // Create
//	void testCreate() {
//		ScoreItem scoreItem = new ScoreItem();
//		scoreItem.setKor(100);
//		scoreItem.setEng(90);
//		scoreItem.setMat(100);
//		
//		scoreItemRepository.save(scoreItem);
//		
//		ScoreItem scoreItem2 = scoreItemRepository.findOneById(1l);
//		assertEquals(100, scoreItem2.getKor());
//		assertEquals(90, scoreItem2.getEng());
//	}
	
//	@Test // Read - All
//	void testFindAll() {
//		List <ScoreItem> list = scoreItemRepository.findAllByOrderByStudentidAsc();
//		for (int i = 0; i < list.size(); i++) {
//			assertEquals(i+209901, list.get(i).getStudentid());
//		}
//	}
	
//	@Test // Read - 페이지 지정
//	void testFindCurrentPage() {
//		List <ScoreItem> list = scoreItemRepository.findCurrentPage(2,15);
//		for (int i = 0; i < list.size(); i++) {
//			assertEquals(i+209916, list.get(i).getStudentid());
//		}
//	}
	
//	@Test // Read - One
//	void testFindOne() {
//		ScoreItem scoreItem = scoreItemRepository.findOneByStudentid(209901);
//		assertEquals("폴리대대장", scoreItem.getName());
//		ScoreItem scoreItem2 = scoreItemRepository.findOneByStudentid(209904);
//		assertEquals("폴리4", scoreItem2.getName());
//	}
	
//	ScoreItem scoreItem = new ScoreItem("폴리대장",209904,99,100,100);
//	@Test // Update
//	void testUpdate() {
//		scoreItemRepository.updateOne(scoreItem);
//		ScoreItem scoreItemUpdated = scoreItemRepository.findOneByStudentid(scoreItem.getStudentid());
//		assertEquals("폴리대장",scoreItemUpdated.getName());
//		assertEquals(99,scoreItemUpdated.getKor());
//	}
	
//	@Test // Delete - One
//	void testDeleteOne() {
//		scoreItemRepository.deleteById(0l);
//		ScoreItem scoreItem = scoreItemRepository.findOneById(0l);
//		assertEquals(null, scoreItem);
//	}
	
//	@Test // Delete - All
//	void testDeleteAll() {
//		scoreItemRepository.deleteAll();
//		List <ScoreItem> list = scoreItemRepository.findAll();
//		assertEquals(0, list.size());
//		
//	}
	
//	@Test // CountAll
//	void testCountAll() {										
//		long count = scoreItemRepository.count();
//		assertEquals(100, count);
//	}

}


