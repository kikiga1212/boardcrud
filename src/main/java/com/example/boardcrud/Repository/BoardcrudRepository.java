package com.example.boardcrud.Repository;


import com.example.boardcrud.Entity.BoardcrudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardcrudRepository extends JpaRepository<BoardcrudEntity, Integer> {
    //Jpa메소드는 findBy필드명 조건논리연산자...
    //findById(값...) : 필드명의 첫글자는 대문자

    /* 일반 데이터베이스 쿼리로 메소드를 만드는 방법
    @Query(value = "select 가져올 필드명 from 테이블명 where 조건", nativeQuery = true )
    List<BoardCrud> 사용자메소드명(변수명)
    */
    //select : 데이터를 조회
    //insert : 데이터를 삽입(저장) => save()
    //update : 데이터를 수정(저장) => save()
    //delete : 데이터를 삭제      => deleteById()
}
