package kr.ac.kopo.kopo18.spring.scoreadmin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.ac.kopo.kopo18.spring.scoreadmin.dto.Pagination;
import kr.ac.kopo.kopo18.spring.scoreadmin.service.ScoreItemService;
import kr.ac.kopo.kopo18.spring.scoreadmin.service.ScoreItemServiceImpl;

//@ContextConfiguration
//@RunWith(SpringRunner.class)
@SpringBootTest
class ScoreItemPaginationTest {
	
	@Autowired
	ScoreItemService scoreItemService;

	@Test // Case 1 : 정상
	void testPagination1() {
		Pagination p = scoreItemService.getPagination(1, 2, 5, 24);
		// 1 : 출력 페이지, 2 : 페이지당 데이터, 5 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, p.getPpPage()); // 가장왼쪽
		assertEquals(1, p.getpPage()); // 왼쪽
		assertEquals(6, p.getnPage()); // 오른쪽
		assertEquals(12, p.getNnPage()); // 가장 오른쪽
		assertEquals(1, p.getcPage()); // 현재 페이지
	}
	
	@Test	// Case 2 : CurrentPage가 0인 경우
	void testPagination2() {
		Pagination p = scoreItemService.getPagination(0, 2, 5, 24);
		// Page index must not be less than zero ==> -200,000 -> 0으로 수정함
		// 0 : 출력 페이지, 2 : 페이지당 데이터, 5 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, p.getPpPage()); // 가장왼쪽
		assertEquals(1, p.getpPage()); // 왼쪽
		assertEquals(6, p.getnPage()); // 오른쪽
		assertEquals(12, p.getNnPage()); // 가장 오른쪽
		assertEquals(1, p.getcPage()); // 현재 페이지
	}

	@Test	// Case 3 : Current Page가 총 페이지보다 큰 경우
	void testPagination3() {
		Pagination p = scoreItemService.getPagination(20, 2, 5, 24);
		// 20 : 출력 페이지, 2 : 페이지당 데이터, 5 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, p.getPpPage()); // 가장왼쪽
		assertEquals(6, p.getpPage()); // 왼쪽
		assertEquals(12, p.getnPage()); // 오른쪽
		assertEquals(12, p.getNnPage()); // 가장 오른쪽
		assertEquals(12, p.getcPage()); // 현재 페이지
	}
	
	@Test	// Case 4 : Current Page가 총 페이지보다 극단적으로 큰 경우
	void testPagination4() {
		Pagination p = scoreItemService.getPagination(100, 2, 5, 24);
		// 100 : 출력 페이지, 2 : 페이지당 데이터, 5 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, p.getPpPage()); // 가장왼쪽
		assertEquals(6, p.getpPage()); // 왼쪽
		assertEquals(12, p.getnPage()); // 오른쪽
		assertEquals(12, p.getNnPage()); // 가장 오른쪽
		assertEquals(12, p.getcPage()); // 현재 페이지
	}
	
	@Test	// Case 5 : 한 페이지당 데이터 수가 총 데이터 수보다 큰 경우 & Current Page가 총 Page 초과하는 경우
	void testPagination5() {
		Pagination pagination = scoreItemService.getPagination(30000, 50, 15, 24);
		// 30000 : 출력 페이지, 50 : 페이지당 데이터, 15 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, pagination.getPpPage());
		assertEquals(1, pagination.getpPage());
		assertEquals(1, pagination.getnPage());
		assertEquals(1, pagination.getNnPage());
		assertEquals(1, pagination.getcPage());
	}
	
	@Test	// Case 6 : 한 페이지당 데이터 수가 총 데이터 수보다 큰 경우 (현재 페이지는 정상)
	void testPagination6() {
		Pagination p = scoreItemService.getPagination(1, 50, 15, 24);
		// 1 : 출력 페이지, 50 : 페이지당 데이터, 15 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, p.getPpPage());
		assertEquals(1, p.getpPage());
		assertEquals(1, p.getnPage());
		assertEquals(1, p.getNnPage());
		assertEquals(1, p.getcPage());
	}
	
	@Test	// Case 7 : 페이지 사이즈가 0인 경우 (예외 처리 1로 함)
	void testPagination7() {
		Pagination p = scoreItemService.getPagination(0, 1, 0, 24);
		// 0 : 출력 페이지, 1 : 페이지당 데이터, 0 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, p.getPpPage()); // 가장왼쪽
		assertEquals(1, p.getpPage()); // 왼쪽
		assertEquals(1, p.getnPage()); // 오른쪽
		assertEquals(1, p.getNnPage()); // 가장 오른쪽
		assertEquals(1, p.getcPage()); // 현재 페이지
	}
	
	@Test // Case 8 : 정상
	void testPagination8() {
		Pagination p = scoreItemService.getPagination(11, 2, 5, 24);
		// 11 : 출력 페이지, 2 : 페이지당 데이터, 5 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, p.getPpPage()); // 가장왼쪽
		assertEquals(6, p.getpPage()); // 왼쪽
		assertEquals(12, p.getnPage()); // 오른쪽
		assertEquals(12, p.getNnPage()); // 가장 오른쪽
		assertEquals(11, p.getcPage()); // 현재 페이지
	}
	
	@Test // Case 9 : 정상
	void testPagination9() {
		Pagination p = scoreItemService.getPagination(7, 2, 5, 24);
		// 7 : 출력 페이지, 2 : 페이지당 데이터, 5 : 페이저 크기 (총 데이터 수 : 24)
		assertEquals(1, p.getPpPage()); // 가장왼쪽
		assertEquals(1, p.getpPage()); // 왼쪽
		assertEquals(11, p.getnPage()); // 오른쪽
		assertEquals(12, p.getNnPage()); // 가장 오른쪽
		assertEquals(7, p.getcPage()); // 현재 페이지
	}
	
//	@Test	// Case 5 : CountPerPage가 0인 경우 
//			// --> PageRequest.of(currentPage, countPerPage) 메서드 사용 시,
//			// CurrentPage는 0보다 작을 수 없고 CounPerPage는 1보다 작을 수 없음!
//	void testPagination4() {
//		Pagination p = scoreItemService.getPagination(2, 0, 5);
//		assertEquals(p.getPpPage(), 1); // 가장왼쪽
//		assertEquals(p.getpPage(), 1); // 왼쪽
//		assertEquals(p.getnPage(), 1); // 오른쪽
//		assertEquals(p.getNnPage(), 1); // 가장 오른쪽
//		assertEquals(p.getcPage(), 1); // 현재 페이지
//	}
	
//	@Test
//	void testPagination5() {
//		Pagination p = scoreItemService.getPagination(-100, 50, 15);
//		assertEquals(p.getPpPage(), 1); // 가장왼쪽
//		assertEquals(p.getpPage(), 1); // 왼쪽
//		assertEquals(p.getnPage(), 1); // 오른쪽
//		assertEquals(p.getNnPage(), 1); // 가장 오른쪽
//		assertEquals(p.getcPage(), 1); // 현재 페이지
//	}
//	
//	@Test
//	void testPagination6() {
//		Pagination p = scoreItemService.getPagination(-10000, 50, 15);
//		assertEquals(p.getPpPage(), 1); // 가장왼쪽
//		assertEquals(p.getpPage(), 1); // 왼쪽
//		assertEquals(p.getnPage(), 1); // 오른쪽
//		assertEquals(p.getNnPage(), 1); // 가장 오른쪽
//		assertEquals(p.getcPage(), 1); // 현재 페이지
//	}
//
//	
//	@Test
//	void testGetPagination2() {
//		Pagination pagination = scoreItemService.getPagination(30000, 50, 15);
//		// 1 : 출력 페이지, 50 : 페이지당 데이터, 15 : 페이저 크기, 1025 : 총 데이터 수
//		assertEquals(1, pagination.getPpPage());
//		assertEquals(1, pagination.getpPage());
//		assertEquals(1, pagination.getnPage());
//		assertEquals(1, pagination.getNnPage());
//		assertEquals(1, pagination.getcPage());
//	}
//	
//	@Test
//	void testGetPagination3() {
//		Pagination pagination = scoreItemService.getPagination(-20, 50, 15);
//		// 1 : 출력 페이지, 50 : 페이지당 데이터, 15 : 페이저 크기, 1025 : 총 데이터 수
//		assertEquals(1, pagination.getPpPage());
//		assertEquals(1, pagination.getpPage());
//		assertEquals(1, pagination.getnPage());
//		assertEquals(1, pagination.getNnPage());
//		assertEquals(1, pagination.getcPage());
//	}

}
