package com.samsung.dao;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cache<T> {

    private List<T> list;

    public void addToList(T element) {
        if(list == null) list = new ArrayList<>();
        list.add(element);
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List getList() {

        return list;
    }
}
