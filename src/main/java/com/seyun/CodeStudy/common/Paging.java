package com.seyun.CodeStudy.common;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Paging<T> {
    private List<T> list = new ArrayList<>();
    private Pagination pagination;

    public Paging(List<T> list, Pagination pagination){
        this.list.addAll(list);
        this.pagination = pagination;
    }
}
