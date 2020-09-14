package com.example.board.web;

import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import com.example.board.dto.HelloResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    PostsRepository postsRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/posts/All")
    public List<Posts> getPostEntity() {
        return postsRepository.findAll();
    }
}
