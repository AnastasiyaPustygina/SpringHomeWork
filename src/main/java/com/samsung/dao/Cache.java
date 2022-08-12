package com.samsung.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Cache<T> {

    private List<T> list;

    public void addToList(T element) {
        if(list == null) list = new ArrayList<T>();
        list.add(element);
    }
}
