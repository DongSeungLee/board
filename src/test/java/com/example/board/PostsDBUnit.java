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
import org.unitils.reflectionassert.ReflectionComparatorMode;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import static org.unitils.reflectionassert.ReflectionAssert.*;
import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;
import static org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_DATES;

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
    @Test
    public void testUnitilsReflectionEquals(){
        Posts entity1 = Posts.builder()
                .title("title6")
                .content("content6")
                .author("author6")
                .build();
        Posts entity2 = Posts.builder()
                .title("title6")
                .content("content6")
                .author("author6")
                .build();
        postsRepository.save(entity2);
        entityManager.detach(entity2);
        // IGNORE_DEFAULTS 기본형에 대한 값 여기서 Long이 null이라서 비교하지 않는다.
        // 이는 expectedValue에 있을 때를 기준으로 한다.
        // LocalDateTime은 무시한다!
        assertReflectionEquals(entity1,entity2,IGNORE_DEFAULTS, LENIENT_DATES);
        
        // prperty 비교
        assertPropertyLenientEquals("title","title6",entity1);
    }
}
