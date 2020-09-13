package com.example.board;

import com.example.board.domain.posts.PostsRepository;
import com.example.board.service.posts.PostsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

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
}
