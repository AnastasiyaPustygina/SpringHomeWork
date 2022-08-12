package com.samsung.dao;

import com.samsung.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
    Question findById(int id);
    void deleteById(int id);
    void save(Question question);
    void changeLanguage(String pathToCsvFile);

}
