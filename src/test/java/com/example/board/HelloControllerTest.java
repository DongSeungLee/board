package com.example.board;

import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import com.example.board.web.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostsRepository postsRepository;
    @Before
    public void setUp(){
        Posts post = Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();
        when(postsRepository.findAll()).thenReturn(Arrays.asList(post));
    }

    @Test
    public void helloReturnTest() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDtoReturnTest() throws Exception {
        String hello = "hello";
        String name = "test";
        int amount = 1000;
        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
    @Test
    public void returnPostsTest() throws Exception {

        mvc.perform(get("/posts/All"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
