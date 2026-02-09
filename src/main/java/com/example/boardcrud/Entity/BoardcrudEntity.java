package com.example.boardcrud.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString   @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "boardcrud")
//기본키를 관리하는 테이블(시퀀스 테이블)
@SequenceGenerator(
        name = "boardcrud_seq", //테이블명
        sequenceName = "boardcrud_seq", //필드명
        initialValue = 1,//시작값
        allocationSize = 1 //증가값
)
public class BoardcrudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardcrud_seq")
    @Column(name = "pid", nullable = false)
    private Integer id;

    @Column(name = "subject", nullable = false, length = 50)
    private String subject;
    @Column(name = "content", length = 100)
    private String content;
}
