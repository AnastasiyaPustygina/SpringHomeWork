package com.samsung.service;

import com.samsung.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAll();
    Question getById(int id);
    void deleteById(int id);
    void save(Question question);

}
