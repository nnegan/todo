package com.todo.backend.v1.board.service.impl;

import com.todo.backend.doamin.board.Board;
import com.todo.backend.doamin.board.BoardRelation;
import com.todo.backend.doamin.board.BoardRepository;
import com.todo.backend.v1.board.model.BoardSearchModel;
import com.todo.backend.v1.board.service.BoardService;
import com.todo.common.model.CommonDataModel;
import com.todo.common.model.PagenatedListModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository repository;


    @Override
    public CommonDataModel<Board> addTodo(Board board) {
        CommonDataModel<Board> result = new CommonDataModel<Board>();

        // 참조하는 다른 todo 항목을 점검한다.
       /* if ( board.getBoardRelation() != null ) {
            for (BoardRelation boardRelation : board.getBoardRelation()) {

                if (!repository.existsById(boardRelation.getRelationBoardNumber())) {
                    result.setReturnCode("ERROR-0010");
                    result.setMessage("연관되어 있는 ToDo항목이 존재하지 않습니다.");
                    result.setData(board);
                    return  result;
                }
            }
        }*/
        repository.save(board);
        result.setReturnCode("SUCCESS");
        result.setMessage("성공");
        result.setData(board);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PagenatedListModel<Board> gettodolist(BoardSearchModel boardSearchModel) {

        if(boardSearchModel.getPage() == null || boardSearchModel.getPage() == 0)
            boardSearchModel.setPage(1); //default page.

        Pageable pageable = PageRequest.of(boardSearchModel.getPage()-1, boardSearchModel.getPageSize());

        // 검색 조건 판단 (text, 완료 여부, 날짜, 등)
        // NO, CONTENT, STATUS, CREATEDAT, UPDATEDAT
        Page<Board> pageBoard = null;
        switch ( boardSearchModel.getSearchDivision() ){
            case "NO" :
                pageBoard = repository.findAllByBoardNumber(boardSearchModel.getBoardNumber(), pageable);
                break;
            case "CONTENT" :
                pageBoard = repository.findAllByBoardContentsLike(boardSearchModel.getBoardContents(), pageable);
                break;
            case "STATUS" :
                pageBoard = repository.findAllByBoardStatus(boardSearchModel.getBoardStatus(), pageable);
                break;
            case "CREATEDAT" :
                pageBoard = repository.findAllByCreatedAt(boardSearchModel.getCreatedAt(), pageable);
                break;
            case "UPDATEDAT" :
                pageBoard = repository.findAllByUpdatedAt(boardSearchModel.getUpdatedAt(), pageable);
                break;
            default:
                pageBoard = repository.findAll(pageable);
        }

        PagenatedListModel<Board> rv = new PagenatedListModel<Board>(pageBoard.getContent());

        rv.setPageSize(pageBoard.getSize());
        rv.setPage(pageBoard.getNumber() + 1);
        rv.setTotalCount((int)pageBoard.getTotalElements());

        return rv;
    }

    @Override
    public CommonDataModel<Board> detailtodo(Long boardnumber) {
        CommonDataModel<Board> result = new CommonDataModel<Board>();

        boolean isempty = repository.existsById(boardnumber);

        // 조회할 데이터가 없음
        if (!isempty){
            result.setReturnCode("ERROR-0100");
            result.setMessage("조회할 ToDo 항목이 없습니다.");
            result.setData(new Board(boardnumber));
            return result;
        }

        result.setData(repository.findAllByBoardNumber(boardnumber));
        result.setReturnCode("SUCCESS");
        result.setMessage("성공");

        return result;
    }

    @Override
    public CommonDataModel<Board>  updatetodo(Board board) {
        CommonDataModel<Board> result = new CommonDataModel<Board>();

        // 참조하는 다른 todo 항목을 점검한다.
       /* if ( board.getBoardRelation() != null ) {
            for (BoardRelation boardRelation : board.getBoardRelation()) {

                if (!repository.existsById(boardRelation.getRelationBoardNumber())) {
                    result.setReturnCode("ERROR-0010");
                    result.setMessage("연관되어 있는 ToDo항목이 존재하지 않습니다.");
                    result.setData(board);
                    return  result;
                }

                Board board_result = repository.getOne(boardRelation.getRelationBoardNumber());
                if (!board_result.getBoardStatus().equals("Y")) { // 완료
                    result.setReturnCode("ERROR-0020");
                    result.setMessage("연관되어 있는 ToDo항목이 완료되지 않았습니다.");
                    result.setData(board);
                    return result;
                }
            }
        }*/

        Optional<Board> oboard = repository.findById(board.getBoardNumber());

        oboard.ifPresent( updateBoard ->{
            updateBoard.setBoardContents(board.getBoardContents());
            updateBoard.setBoardRelation(board.getBoardRelation());
            updateBoard.setBoardStatus(board.getBoardStatus());
            repository.save(updateBoard);
        });
        result.setReturnCode("SUCCESS");
        result.setMessage("성공");
        result.setData(board);

        return result;
    }

    @Override
    public CommonDataModel<Board> deletetodo(Long boardnumber) {
        CommonDataModel<Board> result = new CommonDataModel<Board>();

        boolean isempty = repository.existsById(boardnumber);

        // 삭제할 데이터가 없음
        if (!isempty){
            result.setReturnCode("ERROR-0110");
            result.setMessage("삭제할 ToDo 항목이 없습니다.");
            result.setData(new Board(boardnumber));
            return result;
        }

        repository.deleteById(boardnumber);
        result.setReturnCode("SUCCESS");
        result.setMessage("성공");
        return result;
    }

}
