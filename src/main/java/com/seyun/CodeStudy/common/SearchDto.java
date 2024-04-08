package com.seyun.CodeStudy.common;

import lombok.Data;

@Data
public class SearchDto {
    private int page;
    private int recordSize;
    private int pageSize;
    private String keyword;
    private String searchType;
    private String order;
    private Pagination pagination;


    public SearchDto(){
        this.page = 1;
        this.recordSize = 9;
        this.pageSize = 5;
    }
}
