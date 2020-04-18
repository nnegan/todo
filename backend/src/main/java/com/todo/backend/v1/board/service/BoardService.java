package com.todo.backend.v1.board.service;

import com.todo.backend.doamin.board.Board;
import com.todo.backend.v1.board.model.BoardSearchModel;
import com.todo.common.model.CommonDataModel;
import com.todo.common.model.PagenatedListModel;

public interface BoardService {

    CommonDataModel<Board> addTodo(Board board);

    PagenatedListModel<Board> gettodolist(BoardSearchModel boardSearchModel);

    CommonDataModel<Board> detailtodo(Long boardnumber);

    CommonDataModel<Board>  updatetodo(Board board);

    CommonDataModel<Board>  deletetodo(Long boardnumber);


}
