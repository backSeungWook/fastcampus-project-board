package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.dto.response.ArticleCommentResponse;
import com.fastcampus.projectboard.dto.response.ArticleWithCommentsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * MAIN Controller
 */
@Controller
public class MainController {

    /**
     * root
     *
     * @return
     */
    @GetMapping("/")
    public String root(){
        return "forward:/articles";
    }


    /**
     * 스웨거 테스트
     * @return String
     */
    @ResponseBody
    @GetMapping("/test")
    public ArticleCommentResponse swagger(){
        return ArticleCommentResponse.of(
                1L,
                "content",
                LocalDateTime.now(),
                "email.com",
                "test",
                "test"

        );
    }



}
