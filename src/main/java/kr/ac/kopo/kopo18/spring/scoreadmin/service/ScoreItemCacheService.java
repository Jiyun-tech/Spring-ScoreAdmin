package kr.ac.kopo.kopo18.spring.scoreadmin.service;

public interface ScoreItemCacheService {
	String testNoCache(Long id);
	String testCache(Long id);
	void testCacheClear(Long id);

}
