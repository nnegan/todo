package com.todo.backend.v1.board.model;

import com.todo.backend.doamin.board.Board;
import io.swagger.annotations.ApiModel;

import lombok.Data;



@Data
@ApiModel(description =  "검색 모델")
public class BoardSearchModel extends Board  {

    private String searchDivision = ""; //NO, CONTENT, STATUS, CREATEDAT, UPDATEDAT

    protected Integer page;
    protected Integer pageSize;

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
