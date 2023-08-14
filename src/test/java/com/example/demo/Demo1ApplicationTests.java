package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {

	@Test
	void contextLoads() {
	}
	/**
	 * тест для сообщения в базу данных
	 */
	@Test
	public void testAddingMessage(){

	}

//	@Autowired
//	userRepo userRepos;
//	@Test
//	public void testUsers(){
//		System.out.println("Тест");
//		List list = userRepo.findAll();
//
//		Assertions.assertEquals(28, list.size());
//
//	}
}
