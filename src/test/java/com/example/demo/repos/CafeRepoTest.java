package com.example.demo.repos;

import com.example.demo.model.Cafe;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
class CafeRepoTest {

    private final CafeRepo underTest;

    CafeRepoTest(CafeRepo underTest) {
        this.underTest = underTest;
    }


    @Test
    void itShouldFindCafe() {
         //given
        Cafe cafe = new Cafe(
                "Cafe_1",
                "111111111111111111111111",
                "22222222");
        System.out.println(cafe+"/////////////////////////");
        underTest.save(cafe);
         //when
        boolean exist = underTest.equals(cafe);
        System.out.println(exist+"/////////////////////////");
         //then
        Assert.assertTrue(exist);

    }
}