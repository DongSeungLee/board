package com.example.board;

import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import com.example.board.service.posts.PostsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PostsServiceTest {
    @Mock
    private PostsRepository mockPostsRepository;
    @InjectMocks
    private PostsService postsService;

    private Posts posts;

    @Before
    public void setUp(){
        posts = Posts.builder()
                .content("content")
                .title("title")
                .author("author")
                .build();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindByIdWhenException() {

        Long id = 1L;
        // when
        when(mockPostsRepository.findById(id)).thenThrow(IllegalArgumentException.class);
        // then
        postsService.findById(id);

    }

    @Test
    public void testFindById() {
        long id = 2L;
        Optional<Posts> post = Optional.ofNullable(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        when(mockPostsRepository.findById(id)).thenReturn(post);
        //then
        postsService.findById(id);

        // assert
        verify(mockPostsRepository, times(1)).findById(id);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWhenReturnNull(){
        Long id = 2L;
        // given
        when(mockPostsRepository.findById(id)).thenThrow(IllegalArgumentException.class);

        postsService.delete(2L);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidation(){
        Long id = 2L;
        // mock when void method!
        // doThrow!
        doThrow(new IllegalArgumentException()).when(mockPostsRepository).validation();

        postsService.validation(id);

    }
    @Test
    public void spyTest(){
        List<String> realList = new ArrayList<>();
        realList.add("Hello");
        System.out.println(realList.get(0));
        // 실제 객체 그 자체를 mock으로 만들어 버린다.
        List mockList = spy(realList);
        when(mockList.get(0)).thenReturn("item");
        System.out.println(mockList.get(0));
    }
}
