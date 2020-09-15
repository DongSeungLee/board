package com.example.board;

import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import com.example.board.service.posts.PostsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PostsServiceTest {
    @Mock
    private PostsRepository mockPostsRepository;
    @InjectMocks
    private PostsService postsService;

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
}
