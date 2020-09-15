package com.example.board;

import com.example.board.domain.posts.PostsRepository;
import com.example.board.service.posts.PostsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class Parameterizedtest {
    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[]{
                1,2,3
        });
    }

    private PostsRepository mockPostsRepository;

    private PostsService postsService;
    private int number;
    @Before
    public void setUp(){
        this.mockPostsRepository =mock(PostsRepository.class);
        this.postsService = new PostsService(mockPostsRepository);
        // this is the same with @RunWith(MockitoJUnitRunner.class)
        MockitoAnnotations.initMocks(this);
    }
    public Parameterizedtest(int number){
        this.number = number;

    }
    @Test(expected = IllegalArgumentException.class)
    public void testParameter(){
        System.out.println(this.number);
        Long id = 1L;
        // when
        when(mockPostsRepository.findById(id)).thenThrow(IllegalArgumentException.class);
        // then
        postsService.findById(id);
    }
}
