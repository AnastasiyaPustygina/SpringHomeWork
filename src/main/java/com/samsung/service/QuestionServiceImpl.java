package com.samsung.service;

import com.samsung.dao.QuestionDao;
import com.samsung.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService{

    private QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAll() {
        return dao.findAll();
    }
}
