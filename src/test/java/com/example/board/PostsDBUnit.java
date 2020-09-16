package com.example.board;

import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@Slf4j
public class PostsDBUnit {
    @Autowired
    private PostsRepository postsRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Test
    @DatabaseSetup(
            value = {
                    "/database/Posts.xml"
            })
    @Transactional
    public void inquiry(){
        List<Posts> list = postsRepository.findAll();
        System.out.println(list.size());
        log.info("inquiry test transational : {}", TransactionSynchronizationManager.isActualTransactionActive());
        Posts entity = Posts.builder()
                .title("title6")
                .content("content6")
                .author("author6")
                .build();
        Long id = postsRepository.save(entity).getId();
        entityManager.detach(entity);
        System.out.println(postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException()));
    }
}
