package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
//        @Index(columnList = "hashtag"),
})
@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Setter @ManyToOne(optional = false) private UserAccount userAccount; // 유저 정보 (ID)

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private UserAccount userAccount; // 유저 정보 (ID)

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @ToString.Exclude
    @JoinTable( //중심이 될 테이블의 필드에 붙인다.
            name = "article_hashtag",
            joinColumns = @JoinColumn(name = "articleId"), // 중심이 될 테이블에서 참조 할 컬럼 명
            inverseJoinColumns = @JoinColumn(name = "hashtagId") //상대 방(HashTag)에서 참조 할 컬럼 명
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Hashtag> hashtags = new LinkedHashSet<>();


    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // mappedBy 지정 안해주면 스프링에서 중간 테이블을 만듬.
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @Setter private String hashtag; // 해시태그

    protected Article() {}

    private Article(UserAccount userAccount, String title, String content) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
    }

    public static Article of(UserAccount userAccount, String title, String content) {
        return new Article(userAccount, title, content);
    }

    public void addHashtag(Hashtag hashtag) {
        this.getHashtags().add(hashtag);
    }

    public void addHashtags(Collection<Hashtag> hashtags) {
        this.getHashtags().addAll(hashtags);
    }

    public void clearHashtags() {
        this.getHashtags().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article that)) return false;
        return this.getId() != null && this.getId().equals(that.getId()); // 프록시 객체를 사용하는 하이버네이트의 지연 로딩을 고려하여(n+1), 값 비교를 제대로 수행하지 못하는 일이 없도록 한다.
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());// 프록시 객체를 사용하는 하이버네이트의 지연 로딩을 고려하여(n+1), 값 비교를 제대로 수행하지 못하는 일이 없도록 한다.
    }



}