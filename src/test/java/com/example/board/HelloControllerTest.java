package com.example.board;

import com.example.board.config.auth.SecurityConfig;
import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import com.example.board.web.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostsRepository postsRepository;

    @Before
    public void setUp() {
        Posts post = Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();
        when(postsRepository.findAll()).thenReturn(Arrays.asList(post));
    }
    // SecurityContextHolder.getContext().getAuthentication()을 사용하는 곳이 있을 것이다.
    // 그래서 여기세 Authentication을 채워주기 위해서 WithMockUser가 있어야 한다.
    @Test
    @WithMockUser(value = "tmd3282@naver.com", roles = "USER")
    public void helloReturnTest() throws Exception {
        String hello = "hello";
        // 일단 auth가 null이다.
        // 어디선가 auth가 없으면 302로 바로 status return하는 것으로 보인다.
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    @WithMockUser(roles = "USER")
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
    @WithMockUser(roles = "USER")
    public void returnPostsTest() throws Exception {

        mvc.perform(get("/posts/All"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
