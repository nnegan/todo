package com.todo.backend.doamin.board;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "board" )
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardNumber;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="relation_boardNumber")
    private List<BoardRelation> boardRelation;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String boardContents;

    @Column
    private String boardStatus;

    @Column(updatable = false)
    protected String createdAt;
    @Column
    protected String updatedAt;

    @PrePersist
    protected void onPersist() {
        this.createdAt = this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public Board(Long boardNumber) {
        this.boardNumber = boardNumber;
    }

    public Board(Long boardNumber, List<BoardRelation> boardRelation, String boardContents, String boardStatus, String createdAt, String updatedAt) {
        this.boardNumber = boardNumber;
        this.boardRelation = boardRelation;
        this.boardContents = boardContents;
        this.boardStatus = boardStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Board(List<BoardRelation> boardRelation, String boardContents, String boardStatus) {
        this.boardRelation = boardRelation;
        this.boardContents = boardContents;
        this.boardStatus = boardStatus;
    }

    public Board(String boardContents, String boardStatus) {
        this.boardContents = boardContents;
        this.boardStatus = boardStatus;
    }

    @Getter
    public String getCreatedAt() {
        return createdAt;
    }
    @Getter
    public String getUpdatedAt() {
        return updatedAt;
    }

}