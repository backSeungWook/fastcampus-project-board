# fastcampus-project-board
## 개발 환경
* IntelliJ 2023.3.2 (Community Edition)
* Java 17
* Spring Boot 2.7.0

<br/>

## 강의 자료
> 패스트 캠퍼스 10개 프로젝트로 완성하는 백엔드 웹개발(Java/Spring) 초격차 패키지 Online
<br>* Part 2. 게시판 서비스

* <https://github.com/djkeh/fastcampus-project-board/releases>

## [소프트웨어 버전 작성](https://ko.wikipedia.org/wiki/%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4_%EB%B2%84%EC%A0%84_%EC%9E%91%EC%84%B1)

## 배포
### heroku
```yaml
#application.yaml에 내용 추가
spring:
  config.activate.on-profile: heroku
  datasource:
    url: ${JAWSDB_URL}
  jpa.hibernate.ddl-auto: create
  sql.init.mode: always
```
```properties
#system.properties
java.runtime.version=17
```
```
// build.gradle Heroku 설정
jar {
    manifest {
        attributes('Main-Class': 'com.fastcampus.projectboard.FastCampusProjectBoardApplication')
    }
```
```
//Procfile
web: java $JAVA_OPTS -Dserver.port=$PORT -Dspring.profiles.active=heroku -jar build/libs/project-board-v1.1.jar
```

## Git
### Rebase
Git에서 한 브랜치에서 다른 브랜치로 합치는 방법은 Merge와 Rebase다.  
Merge와 Rebase의 실행결과는 같지만 커밋 히스토리가 달라진다.  
Merge는 쉽고 안전하지만 커밋히스토리가 지저분할 수 있다 반면 Rebase는 잘 모르고 사용할 경우  
위험할 수 있어 까다롭지만 커밋히스토리를 깔끔하게 관리할 수 있다.  
Rebase는 base를 새롭게 설정한다는 의미로 이해하면 좋다.  
[reference](https://velog.io/@kwonh/Git-Rebase%EB%9E%80)


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
### 정적 팩토리 메서드(Static Factory Method)
정적 팩토리 메서드는 객체의 생성을 담당하는 클래스 메서드 이다. 어려운 용어를 더 어렵게 설명한 느낌이다.  

일반적으로 처음 자바를 공부 할 때, 객체를 생성하기 위해서는 new 키워드를 사용한다고 알고 있다.  
그렇다면 메서드를 이용해서 객체를 만들 수 있을까? 라는 생각이 들 수 있다. 반은 맞고 반은 틀리다.  
new 를 직접적으로 사용하지 않을 뿐, 정적 팩토리 메서드라는 클래스 내에 선언되어있는 메서드를  
내부의 new를 이용해 객체를 생성해 반환하는 것이다.  
즉 정적 팩토리 메소드를 통해서 new를 간접적으로 사용한다!  


[참고 자료1](https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%83%9D%EC%84%B1%EC%9E%90-%EB%8C%80%EC%8B%A0-%EC%82%AC%EC%9A%A9%ED%95%98%EC%9E%90)  
[참고 자료2](https://velog.io/@cjh8746/%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9CStatic-Factory-Method)


## Querydsl
build.gradle queryDSL 설정 참조  
![querydsl_gradle_settin.png](image%2Fquerydsl_gradle_settin.png)  

```java
//ex code 
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        // 필드에 대한 기본 검색 ex: where title ="검색 내용"
        QuerydslPredicateExecutor<Article>,
        // 커스텀(Q클래스)
        QuerydslBinderCustomizer<QArticle> 
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);
        // 여기에 들어가져있는 필드에서 만 검색 가능하게(부분 필드 검색)
        bindings.including(root.title, root.content,
                            root.createdAt, root.createdBy);
        // where like '%value'
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); 
        // where like '%value%'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); 
        ...

    }
}
```
### 커스텀 Querydsl
```java
// 구현체
public class ArticleRepositoryCustomImpl 
        extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

        // QuerydslRepositorySupport 상속 받는 구현체에서는 from() 부터 시작
        return from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();
    }
}

// 인테페이스
public interface ArticleRepositoryCustom {
    List<String> findAllDistinctHashtags();
}


// 커스텀 querydsl 적용
@RepositoryRestResource //게시글, 댓글의 json api 를 자동으로 restful 하게 만들게끔 설정
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        // 전체 테이블에 대한 기본 검색 ex) where title ="검색 내용"
        QuerydslPredicateExecutor<Article>,
        // 커스텀(Q클래스)
        QuerydslBinderCustomizer<QArticle> 
{
    ...
}
```
참조
* [reference1](https://ittrue.tistory.com/292)
* [reference2](https://medium.com/mo-zza/spring-data-jpa-querydsl-%EC%A0%81%EC%9A%A9-22a0364cd579)

## OSIV
``특징``  
클라이언트 요청이 들어올 때 영속성 컨텍스트를 생성해서 요청이 끝날 때까지 같은 영속성 컨텍스를  
유지한다. 하여 한 번 조회된 엔티티는 요청이 끝날 때까지 영속 상태를 유지한다.  
엔티티 수정은 트랜잭션이 있는 계층에서만 동작한다. 트랜잭션이 없는 프레젠테이션 계층은  
지연 로딩을 포함해 조회만 할 수 있다.

``단점``  
영속성 컨텍스트와 DB 커넥션은 1:1로 물고있는 관계이기 때문에  
프레젠테이션 로직까지 DB 커넥션 자원을 낭비하게 됨.  
OSIV를 적용하면 같은 영속성 컨텍스트를 여러 트랜잭션이 공유하게될 수도 있다.  
프레젠테이션에서 엔티티를 수정하고 비즈니스 로직을 수행하면 엔티티가 수정될 수 있다.  
프레젠테이션 계층에서 렌더링 과정에서 지연 로딩에 의해 SQL이 실행된다.  
따라서 성능 튜닝시에 확인해야 할 부분이 넓어진다.

```yaml
spring:
  jpa:
    open-in-view: false
```

## JPA
* getReferenceById()  
  함수를 호출하고서, 실제로 그 데이터를 출력하거나 사용할때 SQL이 실행됩니다.  
  (다만, ``ID값을 호출할경우 SQL이 필요하지 않습니다.``)

### ManyToMany
* [다대다 연관관계](https://velog.io/@yuseogi0218/JPA-%EB%8B%A4%EB%8C%80%EB%8B%A4-%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84)  
* [다대다 연관관계 이슈](https://codeung.tistory.com/254)
### N+1 이슈
* [reference1](https://velog.io/@sweet_sumin/JPA-N1-%EC%9D%B4%EC%8A%88%EB%8A%94-%EB%AC%B4%EC%97%87%EC%9D%B4%EA%B3%A0-%ED%95%B4%EA%B2%B0%EC%B1%85%EC%9D%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80%EC%9A%94)

### 순환 참조
* [reference1](https://dev-coco.tistory.com/133)

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
        defaultTemplateResolver.setUseDecoupledLogic(
                thymeleaf3Properties.isDecoupledLogic()
        );

        return defaultTemplateResolver;
    }


    @RequiredArgsConstructor
    @Getter
    @ConstructorBinding
    // 사용자 지정 프로미터 yaml config 에서 사용 할수 있음 
    // ex)spring.thymeleaf3.decoupled-logic: true
    @ConfigurationProperties("spring.thymeleaf3") 
    public static class Thymeleaf3Properties {
        /**
         * Use Thymeleaf 3 Decoupled Logic
         */
        private final boolean decoupledLogic;
    }
```
Main Class 에서 @ConfigurationPropertiesScan 어노테이션 추가

annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'  
추가 시 사용자 지정 프로미터도 자동 완성 지원

### security 태그
```html
<div sec:authorize="isAuthenticated()">
  이 콘텐츠는 인증된 사용자에게만 표시됩니다.
</div>
<div sec:authorize="hasRole('ROLE_ADMIN')">
  이 내용은 관리자에게만 표시됩니다.
</div>
<div sec:authorize="hasRole('ROLE_USER')">
  이 콘텐츠는 사용자에게만 표시됩니다.
</div>
```
sec:authentication 속성은 기록된 사용자 이름과 역할을 인쇄하는 데 사용됩니다
https://www.thymeleaf.org/doc/articles/springsecurity.html

* [reference1](https://gist.github.com/djkeh/6e1d557ce8c466135b1541d342b1c25c)
* [reference2](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#decoupled-template-logic)

## Security
* 스프링 부트 2.7 (스프링 시큐리티 5.7) 부터 시큐리티 설정 방법이 바뀌었다.  
  WebSecurityConfigurerAdapter는 deprecated되었고, SecurityFilterChain을 사용해야 함.  
 
### ``Configuring HttpSecurity``
```java
@Configuration
public class SecurityConfiguration {
    //빈 으로 등록하여 사용을 권장 함.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }
}
```
### ``Configuring WebSecurity``
```java
@Configuration
public class SecurityConfiguration {
    //빈 으로 등록하여 사용을 권장 함.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }
}
```
* [reference1](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.7-Release-Notes#migrating-from-websecurityconfigureradapter-to-securityfilterchain)
* [reference2 - 공식 API 문서](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)         


## JUNIT5
### Mock 객체
* 실제 객체를 만들어 사용하기에 시간, 비용 등의 Cost가 높은경우 사용
* 가짜객체를 만들어 가짜객체가 원하는행위를 하도록 정의하고(가짜객체를 DI)
* 타 컴포넌트에 의존하지 않는 순수한 나의 코드만 테스트하기 위해서 사용

### 컨트롤러에서 테스트코드 작성방법
* MockMvc를 통해 api를 호출하며 해당컨트롤러에서 의존하고 있는 객체를 Mock객체로 만들어 주입해줍니다.(@MockBean 어노테이션 사용)
* Mock 객체는 가짜객체이므로 리턴되는값이 없습니다. 따라서 given, when 등으로 원하는 값을  
  리턴 하도록 미리 정의해줍니다.
* 로직이 진행된후 해당 행위가 진행됐는지 verify를 통해 검증해줍니다.
* 컨트로러는 @SpringBootTest와 @WebMvcTest 어노테이션을 사용하여 테스트하며 해당 포스팅에서는  
  @WebMvcTest를 사용합니다.(@WebMvcTest는 모든 빈을 로드하지않으므로 @MockBean을 사용합니다.)

### 테스트 코드
```java
//MemberController.java 
//테스트대상인 컨트롤러입니다. 

@RestController
@RequestMapping("/api")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member")
    public ResponseEntity<List<Member>> list(){
        List<Member> response = memberService.list();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Member> detail(
            @PathVariable("id") int id
    ) throws Exception {
        Member member = memberService.detail(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/member")
    public ResponseEntity<?> insert(
            @RequestBody Member member
    ) throws Exception {
        int response = memberService.insert(member);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PatchMapping("/member")
    public ResponseEntity<?> update(
            @RequestParam("id") int id, 
            @RequestParam("name") String name
    ) throws Exception {
        Member member = new Member(id,name);
        int response = memberService.update(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) throws Exception {
        int response = memberService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
```

```java
//MemberService
//서비스클래스입니다.(가짜객체를 만들어 사용합니다.)

@Service
public class MemberService {

    MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<Member> list() {
        return memberMapper.selectAllMembers();
    }

    public int insert(Member member) {
        return memberMapper.insertMember(member);
    }

    public int delete(int id) {
        return memberMapper.deleteMember(id);
    }

    public Member detail(int id) {
        return memberMapper.selectById(id);
    }

    public int update(Member member) {
        return memberMapper.updateMember(member);
    }
}

//Memer

@Data
@Builder
public class Member {

  private int id;
  private String name;
}
```

```java
//테스트 코드

@WebMvcTest(MemberController.class)
class MemberControllerUnitTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("멤버 전체조회 테스트")
    void getMemberListTest() throws Exception {
        List<Member> members = new ArrayList<>();
        members.add(Member.builder().name("John").build());

        given(memberService.list()).willReturn(members);

        mvc.perform(get("/api/member"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("John")));
    }

    @Test
    @DisplayName("멤버 추가 테스트")
    void insertMemberTest() throws Exception {
        Member member = Member.builder().name("Tom").build();
        Gson gson = new Gson();
        String content = gson.toJson(member);

        mvc.perform(post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated());

        verify(memberService).insert(member);
    }
}
```
### @WebMvcTest(테스트할 컨트롤러.class)  
해당 클래스만 실제로 로드하여 테스트를 해줍니다.  
아규먼트로 컨트롤러를 지정해주지 않으면 @Controller @RestController @ControllerAdvice 등등  
컨트롤러와 연관된 bean들이 로드됩니다.  
스프링의 모든 빈을 로드하여 테스트하는 방식인 @SpringBootTest어노테이션 대신 컨트롤러 관련 코드만  
테스트하고자 할때 사용하는 어노테이션입니다.


### @Autowired MockMvc mvc  
컨트롤러의 api를 테스트하는 용도인 MockMvc 객체를 주입받습니다.  
perform(httpMethod)로 실행하며 andExpect, andDo, andReturn등으로 동작을 확인하는 방식입니다.


### @MockBean MemberService memberService
MemberController는 MemberService를 스프링컨테이너에서 주입받고있으므로  
가짜 객체를 만들어 컨테이너가 주입할 수 있도록 해줍니다.  
해당객체는 가짜객체이므로 실제 행위를 하는 객체가 아닙니다.  
해당 객체 내부에서 의존하는 객체와 메서드들은 모두 가짜이며 실패하지만 않을뿐 기존에 정해진 동작을   
수행하지 하지 않습니다.


``given(memberService.list()).willReturn(members);``  
가짜객체가 원하는 행위를 할 수 있도록 정의해줍니다.(given when 등을 사용합니다.)  
memberService의 list() 메서드를 실행시키면 members를 리턴해달라는 요청입니다.


``andExpect(content().string(containsString("John")));``  
리턴받은 body에 John이라는 문자열이 존재하는지를 확인합니다.  
given을 통해 mock객체의 예상한 행위가 정상적으로 동작했는지를 확인합니다.


``verify(memberService).insert(member);``  
해당 메서드가 실행됐는지를 검증해줍니다.

``shouldHaveNoInteractions``
해당 메서드가 한번도 실행이 되지 않았을 때.(즉 상호작용이 ㅇ)

### @WithUserDetails(Test)

```java
@WithUserDetails(value="unoTest", setupBefore = TestExecutionEvent.TEST_EXECUTION,
    userDetailsServiceBeanName="userDetailsService" )
@Test
void Test{
        ...
        ...
}
```
* userDetailsServiceBeanName: 내가 등록 한 userDetailsService 한개 만 있을 때는 제외 해도 됨.
* @WithMockUser : 가장 쉽게 특정 유저를 설정해 테스트를 진행하는 방법이다.  
  username, password, roles 등 항목을 입력할 수 있다.  
  커스텀된 Authentication 인증 정보는 사용할 수 없다.
  ```java
  @WithMockUser(username = "test", roles = "USER")
  @Test
  void withMockUserTest() { ... }
  ``` 

### @ParameterizedTest
* 여러 argument를 이용해 테스트를 여러번 돌릴 수 있는 테스트를 할 수 있는 기능
* 사용하기 위해서는 @Test 대신 @ParameterizedTest 를 붙이면 된다.
* @ParameterizedTest를 사용하게 되면 최소 하나의 source 어노테이션을 붙여주어야 한다.   
예를 들어, 다음 테스트는 배열로 argument를 전달하는 @ValueSorce이다
```java
@ParameterizedTest
@MethodSource("parametersProvider")
void methodSourceTest(String str, int num, List<String> list) {
  // (apple, 1, [a, b])랑 (banana, 2, [x, y])
}

static Strema<Arguments> parametersProvider() {
  return Stream.of(
    arguments("apple", 1, Arrays.asList("a", "b")),
    arguments("banana", 2, Arrays.asList("x", "y"))
  );
}


//CsvSource
//,(콤마) 로 분리된 문자열을 테스트 메소드의 파라미터로 넣어준다
@ParameterizedTest
@CsvSource({
        "apple,    1",
        "banana,   2",
        "'lemon, lime', 3"
})
void csvSourceTest(String fruti, int rank) {
  // (apple, 1) (banana, 2) ("lemon, lime", 3)이 각각 들어온다
}
```
```java
//Display Name 조절하기
@DisplayName("Display name of container")
@ParameterizedTest(name = "{index} ==> the rank of ''{0}'' is {1}")
@CsvSource({ "apple, 1", "banana, 2", "'lemon, lime', 3" })
void testWithCustomDisplayNames(String fruit, int rank) {
}
```
``RESULT>``
```
Display name of container ✔
├─ 1 ==> the rank of 'apple' is 1 ✔
├─ 2 ==> the rank of 'banana' is 2 ✔
└─ 3 ==> the rank of 'lemon, lime' is 3 ✔
```

```java

@ParameterizedTest(name = "로또번호 : {0}, 결과 : {1}")
//사용해 복잡한 인수들을 파라미터로 넘길 수 있습니다. Stream 를 반환하는 static 메서드를 작성해주면 됩니다.
@MethodSource("lottoNumbersAndRank")
@DisplayName("맞춘 번호에 따라 등수를 반환한다.")
void findRank(Lotto lotto, Rank rank) {
  assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(rank);
}

private static List<Number> givenNumbers(int... numbers) {
  return Arrays.stream(numbers)
          .mapToObj(Number::new)
          .collect(Collectors.toList());
}

static Stream<Arguments> lottoNumbersAndRank() {
  return Stream.of(
          Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 4, 5, 6)), Rank.FIRST),
          Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 4, 5, 7)), Rank.SECOND),
          Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 4, 5, 9)), Rank.THIRD),
          Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 4, 9, 10)), Rank.FOURTH),
          Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 8, 9, 10)), Rank.FIFTH),
          Arguments.arguments(new Lotto(givenNumbers(1, 2, 8, 9, 10, 11)), Rank.NONE)
  );
}
```

[reference1](https://lannstark.tistory.com/52)  
[reference2](https://github.com/mockito/mockito/wiki/Mockito-features-in-Korean)


## 정규식 패턴
``특정한 규칙을 가진 문자열의 집합을 표현하는 데 사용하는 형식 언어이다``  
정규 표현식이라는 문구는 일치하는 텍스트가 준수해야 하는 "패턴"을 표현하기 위해 특정한 표준의 텍스트 신택스를 의미하기 위해 사용된다.  
[reference1](https://ko.wikipedia.org/wiki/%EC%A0%95%EA%B7%9C_%ED%91%9C%ED%98%84%EC%8B%9D)

## TreeSet
* 정렬을 보장.
```java
  public static ArticleCommentResponse of(
          Long id, 
          String content,
          LocalDateTime createdAt,
          String email,
          String nickname,
          String userId,
          Long parentCommentId
) {
    // 정렬의 규칙 Comparator     
    Comparator<ArticleCommentResponse> childCommentComparator = Comparator
                // .comparing(ArticleCommentResponse::createdAt).reversed() // createdAt 기준 내림차 정렬
                .comparing(ArticleCommentResponse::createdAt) // createdAt 기준으로 (기본은 오름차 정렬) 
                .thenComparingLong(ArticleCommentResponse::id); // id 기준 
        
        return new ArticleCommentResponse(
                id,
                content,
                createdAt,
                email,
                nickname,
                userId,
                parentCommentId,
                new TreeSet<>(childCommentComparator)
        );
    }
```

## oauth2(kakao) 로그인

```yaml

  security:
    oauth2:
      client:
        registration:
          kakao:
            client-id: ${KAKAO_OAUTH_CLIENT_ID}
            client-secret: ${KAKAO_OAUTH_CLIENT_SECRET}
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/kakao"
            client-authentication-method: POST
        provider:
          kakao:
            authorization-uri: https://kauth.kakao.com/oauth/authorize 
            token-uri: https://kauth.kakao.com/oauth/token 
            user-info-uri: https://kapi.kakao.com/v2/user/me # 인증 완료 된 사용자 정보 
            user-name-attribute: id # 고유 식별자
```

### 인증 관련 유저 정보 클래스
```java
public record BoardPrincipal(
        ...
) implements UserDetails, OAuth2User {

  // OAuth2(kakao,naver ..)에서 내려 받은 인증자 정보
  @Override
  public Map<String, Object> getAttributes() { 
    return null;
  }

  // 인증 완료 된 사용자 이름
  @Override
  public String getName() {
    return null;
  }
}
```
* OAuth2User implements 시 구현 해야 하는 메소드 getAttributes() / getName()
* Kakao 인증 후 받은 Response 클래스 구현 KakaoOAuth2Response.java

### 인증 설정 업데이트: OAuth 인증 설정 추가(SecurityConfig.java)
```java
  @Bean
  public SecurityFilterChain securityFilterChain(
          HttpSecurity http,
          OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService
  ) throws Exception {
      return http
              .oauth2Login(oAuth -> oAuth
                      .userInfoEndpoint(userInfo -> userInfo
                              .userService(oAuth2UserService)
                      )
              )
              .build();
  }
  
  //OAuth2UserService 등록
  /**
   * <p>
   * OAuth 2.0 기술을 이용한 인증 정보를 처리한다.
   * 카카오 인증 방식을 선택.
   *
   * <p>
   * TODO: 카카오 도메인에 결합되어 있는 코드. 
   *   확장을 고려하면 별도 인증 처리 서비스 클래스로 분리하는 것이 좋지만, 
   *   현재 다른 OAuth 인증 플랫폼을 사용할 예정이 없어 이렇게 마무리한다.
   *
   * @param userAccountService  게시판 서비스의 사용자 계정을 다루는 서비스 로직
   * @param passwordEncoder 패스워드 암호화 도구
   * @return {@link OAuth2UserService} OAuth2 인증 사용자 정보를 읽어들이고 처리하는 서비스 인스턴스 반환
   */
  @Bean
  public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(
          UserAccountService userAccountService,
          PasswordEncoder passwordEncoder
  ) {
    final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
  
    return userRequest -> {
      OAuth2User oAuth2User = delegate.loadUser(userRequest);
  
      KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
      // yaml  security-oauth2-client-registration 값 즉 kakao
      String registrationId = userRequest.getClientRegistration().getRegistrationId(); 
      String providerId = String.valueOf(kakaoResponse.id());
      String username = registrationId + "_" + providerId;
      String dummyPassword = passwordEncoder.encode("{bcrypt}" + UUID.randomUUID());
  
      return userAccountService.searchUser(username)
              .map(BoardPrincipal::from)// DB에 카카오 인증 로그인 정보가 있을 경우
              .orElseGet(() -> // 카카오 로그인 정보가 없을 경우 즉 처음으로 카카오 연동 로그인 한 경우
                      BoardPrincipal.from(
                              userAccountService.saveUser(
                                      username,
                                      dummyPassword,
                                      kakaoResponse.email(),
                                      kakaoResponse.nickname(),
                                      null
                              )
                      )
              );
    };
  
  }
```

## 어노테이션
### @ToString(callSuper = true)
* 상속 받고 있는 부모 클래스에 있는 필드 까지 ToString으로 만들어주겠다.

### @EntityListeners(AuditingEntityListener.class)
* @EntityListeners : JPA Entity에 Persist, Remove, Update, Load에 대한 event 전과 후에 대한 콜백 메서드를 제공한다.
* AuditingEntityListener.class  : 기본 Entity Auditing 리스너.
```java
@EnableJpaAuditing // Config에서 Auditing 사용 한다고 명시 해줘야 함.
@Configuration
public class JpaConfig {
    
}
```
 
### @MappedSuperclass 
* 여러 테이블(@Entity) 클래스에서 중복되는 컬럼들을 상속으로 받을 수 있게 해줌.


## Vault
HashiCorp Vault는 데이터 암호화와 접근 제어를 통해 비밀 정보 관리를 위한 소프트웨어 도구이다.  
비밀 정보(예: API 키, 비밀번호, 인증서)를 안전하게 저장하고, 사용자 인증과 권한 부여를 통해 엄격하게 제어하며, 감시할 수 있다.   
Vault는 클라우드 환경과 같은 동적 인프라에서 사용하기에 적합하며, 다양한 인증 방법과 통합되어 보안을 강화하는 데 사용된다.  
Vault Configuration 의존성 추가
```
ext {
    set('springCloudVersion', "2021.0.5")
}

dependencies {
  implementation 'org.springframework.cloud:spring-cloud-starter-vault-config'
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}
```
application.yaml: vault 설정 추가
토큰은 보안 정보이므로 별도의 수단을 통해 넣어줘야 한다.

```yaml
spring:
  application.name: fastcampus-board # Vault 설치 후 사이트(localhost:8200)에서 내가 만든 Project 명
  cloud.vault:
    scheme: http
    authentication: TOKEN
    token: ${VAULT_TOKEN}
  config.import: vault://
```

localhost:8200에서 valult 에서 정하는 예시   
``spring.datasource.username=test``

## Swagger UI
REST API를 설계, 빌드, 문서화 및 사용하는 데 도움이되는 OpenAPI 사양을 중심으로 구축 된 오픈 소스 도구 세트입니다
Swagger는 개발한 Rest API를 문서화 한다.  
문서화된 내용을 통해 관리 & API 호출을 통한 테스트를 가능케 한다.  

* 의존성 추가
```
 dependencies {
  implementation 'org.springdoc:springdoc-openapi-ui:1.6.12'
  implementation 'org.springdoc:springdoc-openapi-javadoc:1.6.12'
  
  annotationProcessor 'com.github.therapi:therapi-runtime-javadoc-scribe:0.15.0'
 }

```
* url : ``/swagger-ui``

[reference](https://springdoc.org/#javadoc-support)


## MFA(다중 인증)
Multi-factor authentication, MFA)은 적어도 다음 분류 중 두 가지에 한해 별도의 여러 증거 부분을 인증 매커니즘에  
성공적으로 제시한 이후에만 사용자가 접근 권한이 주어지는 컴퓨터 접근 제어 방식의 하나이다.

대중적인 여러 웹 서비스들은 다요소 인증을 사용하고 있으며, 일반적으로 기본값으로 비활성화되는 선택 기능이다.

2요소 인증(Two-factor authentication)
수많은 인터넷 서비스(구글, 아마존 AWS 등)는 개방형 시간 기반 일회용 비밀번호 알고리즘(TOTP)을  
사용하여 다요소 또는 2요소 인증을 지원한다
