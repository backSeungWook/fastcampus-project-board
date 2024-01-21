package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass // 여러 테이블에서 중복되는 컬럼들을 상속으로 받을 수 있게 해줌.
public abstract class AuditingFields {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // Web에서 보여질 때 포멧
    @CreatedDate // 저장(Insert)될 때 시간이 자동으로 저장
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt; // 생성일시

    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100)
    protected String createdBy; // 생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate // Entity의 값을 변경할 때 시간이 자동으로 저장
    @Column(nullable = false)
    protected LocalDateTime modifiedAt; // 수정일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    protected String modifiedBy; // 수정자

}