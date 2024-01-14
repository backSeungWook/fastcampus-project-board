# fastcampus-project-board
## 개발 환경
* IntelliJ 2023.3.2 (Community Edition)
* Java 17
* Spring Boot 2.7.0


## 강의 자료
> 패스트 캠퍼스 10개 프로젝트로 완성하는 백엔드 웹개발(Java/Spring) 초격차 패키지 Online
<br>* Part 2. 게시판 서비스

* <https://github.com/djkeh/fastcampus-project-board/releases>


## API Endpoint
| 종류                                                              | 	uri         | 	method            | 	기능	         | 설명 |
|-----------------------------------------------------------------|--------------|--------------------|--------------|----| 
| 뷰	/                                                             | 	GET         | 	루트 페이지            | 	게시판 페이지로 이동 |
| /error                                                          | 	GET         | 	에러 페이지            |
| /login                                                          | GET          | 로그인 페이지            |
| /sign-up                                                        | GET          | 회원 가입 페이지          |
| /articles                                                       | GET          | 게시판 페이지            |
| /articles/{article-id}                                          | GET          | 게시글 페이지            |
| /articles/search                                                | GET          | 게시판 검색 전용 페이지      |
| /articles/search-hashtag                                        | GET          | 게시판 해시태그 검색 전용 페이지 |
| api                                                             | /api/sign-up | POST               | 회원 가입        |
| /api/login                                                      | GET          | 로그인 요청             |
| /api/articles                                                   | GET          | 게시글 리스트 조회        |
| /api/articles/{article-id}                                      | GET          | 게시글 단일 조회          |
| /api/articles                                                   | POST         | 게시글 추가             |
| /api/articles/{article-id}                                      | PUT, PATCH   | 게시글 수정             |
| /api/articles/{article-id}                                      | DELETE       | 게시글 삭제             |
| /api/articleComments                                            | GET          | 댓글 리스트 조회          |
| /api/articleComments/{article-comment-id}                       | GET          | 댓글 단일 조회           |
| /api/articles/{article-id}/articleComments                      | GET          | 게시글에 연관된 댓글 리스트 조회 |
| /api/articles/{article-id}/articleComments/{article-comment-id} | GET          | 게시글에 연관된 댓글 단일 조회  |
| /api/articles/{article-id}/articleComments                      | POST         | 댓글 등록              |
| /api/articles/{article-id}/articleComments/{article-comment-id} | PUT, PATCH   | 댓글 수정              |
| /api/articles/{article-id}/articleComments/{article-comment-id} | DELETE       | 댓글 삭제              |              |




| uri                                                             | method     | 입력 데이터 구상                  | request body | response body |
|-----------------------------------------------------------------|------------|----------------------------|--------------|---------------|
| /api/sign-up                                                    | POST	      | id, 비밀번호, 이메일, 닉네임, 메모	    |              |               |
| /api/login                                                      | GET        | id, 비밀번호	                  |              |               |
| /api/articles                                                   | 	GET	      | 필터: 제목, 본문, id, 닉네임, 해시태그	 |              |               |
| /api/articles/{article-id}                                      | 	GET		     |                            |              |               |
| /api/articles                                                   | POST       | 제목, 본문, id, 해시태그	          |              |               |
| /api/articles/{article-id}                                      | PUT, PATCH | 제목, 본문, 해시태그               |              |               |
| /api/articles/{article-id}                                      | DELETE     | 게시글 id                     |              |               |
| /api/articleComments                                            | GET        | 필터: 본문, id, 닉네임	           |              |               |
| /api/articleComments/{article-comment-id}                       | GET        |                            |              |               |
| /api/articles/{article-id}/articleComments                      | GET        | 필터: 본문                     |              |               |
| /api/articles/{article-id}/articleComments/{article-comment-id} | GET        |                            |              |               |
| /api/articles/{article-id}/articleComments                      | POST       | 본문, id                     |              |               |
| /api/articles/{article-id}/articleComments/{article-comment-id} | PUT, PATCH | 본문	                        |              |               |
| /api/articles/{article-id}/articleComments/{article-comment-id} | DELETE     | 댓글 id	                     |              |               |


## 정적 팩토리 메서드(Static Factory Method)
```java
   // 예시
   public static Article of(UserAccount userAccount, String title, String content) {
        return new Article(userAccount, title, content);
    }
```
[참고 자료1](https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%83%9D%EC%84%B1%EC%9E%90-%EB%8C%80%EC%8B%A0-%EC%82%AC%EC%9A%A9%ED%95%98%EC%9E%90)  
[참고 자료2](https://velog.io/@cjh8746/%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9CStatic-Factory-Method)


## Querydsl
build.gradle queryDSL 설정 참조  
ex code 
```java
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,// 전체 테이블에 대한 기본 검색 ex) where title ="검색 내용"
        QuerydslBinderCustomizer<QArticle> // 커스텀(Q클래스)
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.createdAt, root.createdBy); // 여기에 들어가져있는 필드에서 만 검색 가능하게(부분 필드 검색)
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // where like '%value'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // where like '%value%'
        ...

    }
}
```
참조   
* https://ittrue.tistory.com/292   
* https://medium.com/mo-zza/spring-data-jpa-querydsl-%EC%A0%81%EC%9A%A9-22a0364cd579


## Thymeleaf
thymeleaf decoupled logic : 순수 html과 Thymeleaf 구분  
Thymeleaf : ex) header.th.xml 확장자가 xml  
ThymeleafConfig 생성 후 설정 필용   
```java
@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());

        return defaultTemplateResolver;
    }


    @RequiredArgsConstructor
    @Getter
    @ConstructorBinding
    @ConfigurationProperties("spring.thymeleaf3") // 사용자 지정 프로미터 yaml config 에서 사용 할수 있음 ex)spring.thymeleaf3.decoupled-logic: true
    public static class Thymeleaf3Properties {
        /**
         * Use Thymeleaf 3 Decoupled Logic
         */
        private final boolean decoupledLogic;
    }
```
Main Class 에서 @ConfigurationPropertiesScan 어노테이션 추가

annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'  추가 시 사용자 지정 프로미터도 자동 완성 지원

* 참고 코드: https://gist.github.com/djkeh/6e1d557ce8c466135b1541d342b1c25c
* 참고 문서: https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#decoupled-template-logic

## Security
* 스프링 부트 2.7 (스프링 시큐리티 5.7) 부터 시큐리티 설정 방법이 바뀌었다. WebSecurityConfigurerAdapter는 deprecated되었고, SecurityFilterChain을 사용해야 함.
* https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.7-Release-Notes#migrating-from-websecurityconfigureradapter-to-securityfilterchain
* https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

## OSIV
```yaml
spring:
  jpa:
    open-in-view: false
```

## JPA
* getReferenceById()