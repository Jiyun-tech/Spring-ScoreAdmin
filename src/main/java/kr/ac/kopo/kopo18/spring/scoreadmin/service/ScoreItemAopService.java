package kr.ac.kopo.kopo18.spring.scoreadmin.service;

public interface ScoreItemAopService {
	
	// Aop
	void testAopBefore();
	void testAopAfter();
	String testAopAfterReturning();
	void testAopAfterThrowing();
	void testAopAround();

}
