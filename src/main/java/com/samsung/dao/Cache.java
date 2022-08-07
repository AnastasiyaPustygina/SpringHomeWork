package com.samsung.dao;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cache<T> {

    private List list;

    public <E> void setList(List<E> list) {
        this.list = list;
    }

    public List getList() {

        return list;
    }
}
