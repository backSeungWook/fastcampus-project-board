package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //게시글, 댓글의 json api 를 자동으로 restful 하게 만들게끔 설정
public interface ArticleRepository extends JpaRepository<Article, Long> {
}