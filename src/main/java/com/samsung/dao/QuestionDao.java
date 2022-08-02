package com.samsung.dao;

import com.samsung.domain.Question;

import java.util.List;

public interface QuestionDao {
    public List<Question> findAll();
}
