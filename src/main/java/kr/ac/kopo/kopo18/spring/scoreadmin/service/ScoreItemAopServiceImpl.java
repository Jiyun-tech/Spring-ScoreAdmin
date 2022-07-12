package kr.ac.kopo.kopo18.spring.scoreadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.kopo18.spring.scoreadmin.repository.ScoreItemRepository;

@Service
public class ScoreItemAopServiceImpl implements ScoreItemAopService {

	@Override
	public void testAopBefore() {
		System.out.println("ScoreItemServiceImple.testAopBefore() 메소드 호출");
	}

	@Override
	public void testAopAfter() {
		System.out.println("ScoreItemServiceImple.testAopAfter() 메소드 호출");
	}

	@Override
	public String testAopAfterReturning() {
		System.out.println("ScoreItemServiceImple.testAopAfterReturning() 메소드 호출");
		return "Success";
	}

	@Override
	public void testAopAfterThrowing() {
		System.out.println("ScoreItemServiceImple.testAopAfterThrowing() 메소드 호출");
		throw new RuntimeException("runtime exception 발생");
	}

	@Override
	public void testAopAround() {
		System.out.println("ScoreItemServiceImple.testAopAround() 메소드 호출");
	}

}
