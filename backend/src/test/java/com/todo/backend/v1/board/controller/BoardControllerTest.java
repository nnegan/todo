package com.todo.backend.v1.board.controller;

import com.todo.backend.doamin.board.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class BoardControllerTest {


    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void addtodo() {

        Board requestBoard = new Board("테스트 입력", "N");
        String result = testRestTemplate.postForObject("/backend/v1/todo/add", requestBoard, String.class);
        log.info("result : {}", result);

    }

    @Test
    void gettodolist() {
        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello saelobi");

    }

    @Test
    void updateodo() {
    }

    @Test
    void delete() {
    }

    @Test
    void updatstatus() {
    }

    @Test
    void backup() {
    }
}