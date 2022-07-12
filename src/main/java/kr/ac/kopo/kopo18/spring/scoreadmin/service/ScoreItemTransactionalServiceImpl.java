package kr.ac.kopo.kopo18.spring.scoreadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreItem;
import kr.ac.kopo.kopo18.spring.scoreadmin.repository.ScoreItemRepository;

@Service // Component -> Singleton으로 처리
public class ScoreItemTransactionalServiceImpl implements ScoreItemTransactionalService{
	
	@Autowired
	ScoreItemRepository scoreItemRepository;
	
	// Transactional
		@Override
		public String testNoTransactional() {
			ScoreItem scoreItem = scoreItemRepository.findById(1L).get();
			scoreItem.setEng(66);
			scoreItemRepository.save(scoreItem);
			
			throw new RuntimeException("Spring Boot No Transactional Test");
		}

		@Override
		@Transactional
		public String testTransactional() {
			ScoreItem scoreItem = scoreItemRepository.findById(1L).get();
			scoreItem.setEng(66);
			scoreItemRepository.save(scoreItem);
			
			throw new RuntimeException("Spring Boot Transactional Test");
		}

}
