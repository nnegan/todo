package com.todo.backend.doamin.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByBoardNumber(Long boardNumber, Pageable pageRequest);

    Page<Board> findAllByBoardContentsLike(String boardContents, Pageable pageRequest);

    Page<Board> findAllByBoardStatus(String boardStatus, Pageable pageRequest);

    Page<Board> findAllByCreatedAt(String createAt, Pageable pageRequest);

    Page<Board> findAllByUpdatedAt(String updatedAt, Pageable pageRequest);

    Board findAllByBoardNumber(Long boardNumber);

}
