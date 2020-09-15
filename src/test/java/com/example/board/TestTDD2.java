package com.example.board;


import com.example.board.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;

public class TestTDD2 {

    @Autowired
    private MockMvc mvc;
    private User user;

    @Before
    public void setUp() {
        user = User.builder()
                .name("hoho")
                .email("tmd3282")
                .build();
    }

    @Test(expected = NumberFormatException.class)
    public void testNumberFormatException() {
        String a = "a018";
        System.out.println(Integer.parseInt(a));
        System.out.println("testNumberFormatException: " + user);
    }
    @Test
    public void testA() {
        System.out.println("testA: " + user);
    }
    @Test
    public void testB(){
        // testA와의 user reference가 다른 것을 확인할 수 있다.
        System.out.println("testB: " + user);
        assertThat(1).isEqualTo(1);
    }
}
