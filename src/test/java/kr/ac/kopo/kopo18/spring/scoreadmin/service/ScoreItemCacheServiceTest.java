package kr.ac.kopo.kopo18.spring.scoreadmin.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScoreItemCacheServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ScoreItemCacheServiceImpl.class);
	
	@Autowired
	private ScoreItemCacheService scoreItemCacheService;
	
	private long startTime;
	private long endTime;
	
	@Before
	public void onBefore() {
		startTime = System.currentTimeMillis();
	}
	
	@After
	public void onAfter() {
		endTime = System.currentTimeMillis();
		logger.info("소요시간 : {}ms", endTime - startTime);
	}

	@Test
	void testNoCache() {
		scoreItemCacheService.testNoCache(1L);
	}
	
	@Test
	void testCache1() {
		scoreItemCacheService.testCache(1L);
	}
	
	@Test
	void testCache2() {
		scoreItemCacheService.testCache(1L);
	}
	
	@Test
	void testCache3() {
		scoreItemCacheService.testCache(2L);
	}
	
	@Test
	void testCache4() {
		scoreItemCacheService.testCache(1L);
	}
	
	@Test
	void testCache5() {
		scoreItemCacheService.testCacheClear(1L);
		scoreItemCacheService.testCache(1L);
	}

}
