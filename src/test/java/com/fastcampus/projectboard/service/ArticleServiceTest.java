package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@DisplayName("비지니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class) // 서비스 로직만 테스트 (스프링 부트가 켜지지 않아 빠른 테스트)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock private ArticleRepository articleRepository;

    @DisplayName("검색어 없이 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenNoSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        // Given
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }

    @DisplayName("검색어와 함께 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(searchType, searchKeyword, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchKeyword, pageable);
    }


    @DisplayName("게시글을 조회하면, 게시글 반환.")
    @Test
    void articleId_SearchingArticle_returnArticle(){

        ArticleDto articleDto = sut.searchArticles(1L);

        assertThat(articleDto).isNotNull();

    }

    @DisplayName("게시글 정보를 입력하면, 게시그을 생성")
    @Test
    void saveArticle(){

        given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);

//        sut.saveArticle(ArticleDto.of(,"title","content"));

        //save가 실행이 되었는지
        then(articleRepository).should().save(ArgumentMatchers.any(Article.class));

    }

    @DisplayName("게시글의 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenModifiedArticleInfo_whenUpdatingArticle_thenUpdatesArticle() {
        given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);

//        sut.updateArticle(1L, ArticleUpdateDto.of("title","content","#java"));

        then(articleRepository).should().save(ArgumentMatchers.any(Article.class));


    }

    @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {

        // 아무런 일도 하지 않고 코드 적인 명시
        BDDMockito.willDoNothing().given(articleRepository).delete(ArgumentMatchers.any(Article.class));

        sut.deleteArticle(1L);

        then(articleRepository).should().delete(ArgumentMatchers.any(Article.class));


    }

}