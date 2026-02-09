package com.example.boardcrud.Controller;

import com.example.boardcrud.DTO.BoardcrudDTO;
import com.example.boardcrud.Service.BoardcrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//서비스의 메소드 개수만큼
//html의 페이지 개수만큼
//목록폼, 삽입폼, 수정폼, 상세폼, 삭제 => 매핑명 지정
//테이블명/작업명 =>매핍명(springBoot 방식)
//테이블명/작업명/{대상} =>매핍명(rest방식)
//메소드명은 service와 비슷하게

@Controller
@RequiredArgsConstructor
public class boardcrudController {
    private final BoardcrudService boardcrudService;

    //전체조회
    @GetMapping("/board")
    public String getAlls(Model model){
        List<BoardcrudDTO> list = boardcrudService.getAlls();//비지니스 로직
        //model을 통해서 html에 전달되는 변수명을 기억 "변수명"
        model.addAttribute("boardcrudDTOS", list);
        return "board/list";
    }

    //?id=12 => @Getparam()생략가능 , 변수이름 노출됨
    //board/12 => @PathVariable
    //개별조회
    @GetMapping("/board/{id}")
    public String getById(@PathVariable Integer id, Model model){
        BoardcrudDTO dto = boardcrudService.getBYId(id);
        model.addAttribute("boardcrudDTO", dto);
        return "board/read"; //테이블 1개당 폴더1개씩 지정
    }

    //postMapping은 일반적으로 redirect로 다른 맵핑으로 이동
    //삭제
    @PostMapping("/board/{id}")
    public String delete(@PathVariable Integer id){
        //전달받은 값을 사용할 일이 없으면 생략가능
        //boolean delete = boardcrudService.delete(id);
        boardcrudService.delete(id);

        return "redirect:/board";
    }

    //삽입과 수정은 get(페이지로 이동)과 post(데이터베이스 저장) 2개가 필요
    @GetMapping("/board/insert")
    public String insertForm(){
        //검증시 model을 통해서 빈 BoardcrudDTO를 전달
        return "board/insert";
    }
    @PostMapping("/board/insert")
    public String insertProc(BoardcrudDTO boardcrudDTO){
        boardcrudService.save(boardcrudDTO);
        return "redirect:/board";
    }

    @GetMapping("/board/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model){
        BoardcrudDTO dto = boardcrudService.getBYId(id);
        model.addAttribute("boardcrudDTO", dto);
        return "board/update";
    }

    @PostMapping("/board/update/{id}")
    public String updateProc(@PathVariable Integer id, BoardcrudDTO boardcrudDTO){
        boardcrudService.update(boardcrudDTO);

        return "redirect:/board";
    }

    //Controller의 역할 : 클라이언트<-DTO->Controller<-DTO->Service

    @DeleteMapping("/board/{id}")
    public String deleteProc(@PathVariable Integer id){
        return "redirect:/board";
    }
}

