package kr.ac.kopo.kopo18.spring.scoreadmin.service;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScoreItemTransactionalServiceTest {
	
	@Autowired
	private ScoreItemTransactionalService scoreItemTransactionalService;
	
	
	@Ignore
	@Test
	public void testNoTranscational() throws RuntimeException {
		scoreItemTransactionalService.testNoTransactional();
	}
	
	@Test
	public void testTranscational() throws RuntimeException {
		scoreItemTransactionalService.testTransactional();
	}

}
