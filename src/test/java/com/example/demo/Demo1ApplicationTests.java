package com.example.demo;

import com.example.demo.repos.CafeRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {


	@Test
	public void test() {
		int x = 2;
		int y = 5;
		Assert.assertEquals(10, x * y);

	}


	@Test
	void contextLoads() {
	}

	/**
	 * тест для сообщения в базу данных
	 */
	@Test
	public void testAddingMessage() {

	}


	@Test
	public void testAllRepoBySize() {


	}


		@Test
		void itShouldFindCafe() {

			CafeRepo underTest;
			System.out.println("???????????????????");

		}
	
}
