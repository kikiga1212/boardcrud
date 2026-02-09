package com.example.boardcrud.Service;

import com.example.boardcrud.DTO.BoardcrudDTO;
import com.example.boardcrud.Entity.BoardcrudEntity;
import com.example.boardcrud.Repository.BoardcrudRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//클라이언트로 전달받은 값을 데이터베이스에 어떻게 작업
//데이터베이스로 작업가능(추가,수정,삭제,조회(전체,개별))
@Service
@RequiredArgsConstructor
public class BoardcrudService {
    //변환기, 사용할 Repository
    private final BoardcrudRepository boardcrudRepository;
    private final ModelMapper modelMapper;

    //반드시 만든 메소드명, 전달받은 값, 결과값을 알아야 한다.

    //저장(클라이언트->DTO->Controller
    // ->DTO->Service->Entity->데이터베이스 저장->DTO(Entity)전달)
    //void 유효성검사(X), DTO(Entity) 유효성검사(O)
    //저장(save, create, insert 이름)
//    public void save(BoardcrudDTO boardcrudDTO){
//        BoardcrudEntity entity = modelMapper.map(boardcrudDTO, BoardcrudEntity.class);
//        boardcrudRepository.save(entity);
//    }
    public BoardcrudEntity save(BoardcrudDTO boardcrudDTO){
        BoardcrudEntity entity = modelMapper.map(boardcrudDTO, BoardcrudEntity.class);
        return boardcrudRepository.save(entity);
    }

    //수정(update, modify 이름)
    // ->DTO->Service->id를 조회->조회한 Entity에 dto를 전용->데이터베이스 저장->DTO(Entity)전달)
    //id를 변수로 따로 받아서 처리(대화상자를 이용해서 입력/수정처리)
    @Transactional //조회된 내용의 변경사항을 자돟ㅇ으로 적용(save 생략이 가능)
    public BoardcrudEntity update(BoardcrudDTO boardcrudDTO){
        //optional를 사용할때는 entity,get()을 이용해서 사용
        //Optional<BoardcrudEntity> entity = boardcrudRepository.findById(boardcrudDTO.getId());
        //if(entity.isPresent()) 또는 if(entity.isEmpty())
        //entity.get().setSubject(boardcrudDTO.getSubject());
        //entity.get().setContent(boardcrudDTO.getContent());


        //optional이 없으면 orElse예외처리를 추가
        BoardcrudEntity entity = boardcrudRepository.findById(boardcrudDTO.getId()).orElse(null);
        entity.setSubject(boardcrudDTO.getSubject()); //DTO에서 수정할
        entity.setContent(boardcrudDTO.getContent()); //Entity에 저장

        return boardcrudRepository.save(entity);
    }

    //삭제(delete, remove 이름)
    //클라이언트->id->Controller->id->Service->데이터베이스 삭제
//    public void delete(Integer id){
//        boardcrudRepository.deleteById(id);
//    }
    public boolean delete(Integer id){
        Optional<BoardcrudEntity> entity = boardcrudRepository.findById(id);
        if(entity.isPresent()){
            boardcrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 전체조회
    //클라이언트->Controller->Service->전체조회->List<DTO>
    //목록읽기, 목록정렬해서 읽기, 목록 검색어 읽기
    // => 페이지 읽기, 페이지 정렬해서 읽기, 페이지 검색어 읽기
    public List<BoardcrudDTO> getAlls(){
        List<BoardcrudEntity> entityList = boardcrudRepository.findAll();
        List<BoardcrudDTO> dtoList = Arrays.asList(
                modelMapper.map(entityList, BoardcrudDTO[].class));
        return dtoList;
    }

    //개별조회
    //클라이언트->id->Controller->id->Service->조회->DTO전달
    public BoardcrudDTO getBYId(Integer id){
        Optional<BoardcrudEntity> entity = boardcrudRepository.findById(id);
        BoardcrudDTO dto = modelMapper.map(entity, BoardcrudDTO.class);
        return dto;
    }
}
