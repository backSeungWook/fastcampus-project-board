package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource //게시글, 댓글의 json api 를 자동으로 restful 하게 만들게끔 설정
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<Article>,// 전체 테이블에 대한 기본 검색 ex) where title ="검색 내용"
        QuerydslBinderCustomizer<QArticle> // 커스텀
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.createdAt, root.createdBy); // 여기에 들어가져있는 필드에서 만 검색 가능하게(부분 필드 검색)
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // where like '%value'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // where like '%value%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // where like '%value%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // where like '%value%'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // where like '%value%'
    }

    List<ArticleComment> findByArticle_Id(Long articleId);// 게시글 아이디를 통해서 게시글 아이디에 해당하는 댓글들의 리스트(게시글로 댓글들을)
    void deleteByIdAndUserAccount_UserId(Long articleCommentId, String userId);
}