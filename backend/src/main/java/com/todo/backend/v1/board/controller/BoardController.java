package com.todo.backend.v1.board.controller;

import com.todo.backend.doamin.board.Board;
import com.todo.backend.v1.board.model.BoardSearchModel;
import com.todo.backend.v1.board.service.BoardService;
import com.todo.common.model.CommonDataModel;
import com.todo.common.model.PagenatedListModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/todo")
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("/add")
    @ApiOperation(value="todo 항목을 추가", notes="todo 항목을 추가할 수 있으며 다른 todo 항목을 참조 할 수 있다.")
    public CommonDataModel<Board> addtodo(@RequestBody Board board){
        return boardService.addTodo(board);
    }

    @GetMapping("/list")
    @ApiOperation(value="todo 목록을 조회", notes="작성 일자, 최종 수정 일자, 내용, 참조하고 있는 todo들의 id가 표시되어야 한다. 페이징처리")
    public PagenatedListModel<Board> gettodolist(@ModelAttribute BoardSearchModel boardSearchModel){
        return boardService.gettodolist(boardSearchModel);
    }

    @GetMapping("/detail/{boardnumber}")
    @ApiOperation(value="todo 항목의 상세", notes="todo 항목의 상세정보를 볼 수 있다.")
    public CommonDataModel<Board> detail(@PathVariable Long boardnumber){
        return boardService.detailtodo(boardnumber);
    }

    @PostMapping("/update")
    @ApiOperation(value="todo 항목을 수정", notes="todo 항목을 수정 할 수 있으며 다른 todo 항목을 참조도 수정가능, 상태값도 수정 가능하다")
    public CommonDataModel<Board> updateodo(@RequestBody Board board){
        return boardService.updatetodo(board);
    }

    @PutMapping("/delete/{boardnumber}")
    @ApiOperation(value="todo 항목을 삭제", notes="todo 항목을 삭제할 수 있다.")
    public CommonDataModel<Board> delete(@PathVariable Long boardnumber){
        return boardService.deletetodo(boardnumber);
    }

}
