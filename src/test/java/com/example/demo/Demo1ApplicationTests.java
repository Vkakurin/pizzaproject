package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Demo1ApplicationTests {

	@Test
	void contextLoads() {
	}

	/**
	 * тест для сообщения в базу данных
	 */
	@Test
	public void testAddingMessage() {

	}
//
//		@Test
//		public void testUsers() {
//			@Autowired
//			userRepo userRepos;
//
//			System.out.println("Тест");
//			List list = userRepo.findAll();
//
//			Assertions.assertEquals(28, list.size());
//
//		}
	}
