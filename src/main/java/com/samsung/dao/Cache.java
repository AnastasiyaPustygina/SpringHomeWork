package com.samsung.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@NoArgsConstructor
public class Cache<T> {

    private List<T> list;

    public void addToList(T element) {
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(element);
    }
    public void removeFromList(T element){
        list.remove(element);
    }
    public void clearList(){
        list.clear();
    }
}
