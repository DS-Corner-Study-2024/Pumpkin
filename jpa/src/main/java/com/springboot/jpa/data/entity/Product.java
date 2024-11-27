package com.springboot.jpa.data.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

import lombok.*;

@Entity // 클래스와 테이블 일대일 매칭.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "name")
@Table(name = "product")    // 클래스명과 테이블명이 다를 때 테이블명 명시.
public class Product {

    @Id // 필수 어노테이션.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 필드의 값을 자동 생성. 일반적으로 @Id와 함께 사용.
    // AUTO: IDENTITY, SEQUENCE, TABLE 세 전략 중 자동 선택
    // IDENTITY: 데이터베이스의 AUTO_INCREMENT를 사용해 기본값을 생성.
    // SEQUENCE: @SequenceGenerator 어노테이션으로 식별자 생성기를 정의해 값을 주입받음.
    // TABLE: 모든 DBMS에서 동일하게 동작. @TableGenerator 어노테이션으로 별도의 테이블을 생성하여 값을 갱신.
    private Long number;

    @Column(nullable = false)   // 필드에 설정을 더할 때 사용. (name, nullable, length, unique)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // @Transient: 데이터베이스에서 필요 없는 필드에 사용하는 어노테이션.

}
