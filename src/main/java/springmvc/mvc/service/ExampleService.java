package springmvc.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExampleService {
	
	public void invokeWithoutTransaction() {
		System.out.println("Hello world!");
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void invokeWithTransaction() {
		System.out.println("Hello world!");
	}
}
