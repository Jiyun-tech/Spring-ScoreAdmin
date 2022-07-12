package kr.ac.kopo.kopo18.spring.scoreadmin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScoreItemAopServiceTest {
	
	@Autowired
	ScoreItemAopService scoreItemAopService;
	
//	@Test
//	void testAopBefore() {
//		scoreItemAopService.testAopBefore();
//	}
//
//	@Test
//	void testAopAfter() {
//		scoreItemAopService.testAopAfter();
//	}
//	
//	@Test
//	void testAopAfterReturning() {
//		scoreItemAopService.testAopAfterReturning();
//	}
	
	@Test
	void testAopAfterThrowing() {
		scoreItemAopService.testAopAfterThrowing();
	}
	
//	@Test
//	void testAopAround() {
//		scoreItemAopService.testAopAround();
//	}

}
