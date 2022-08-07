package com.samsung.service;

import com.samsung.dao.QuestionDao;
import com.samsung.domain.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private QuestionDao dao;

    @Override
    public List<Question> getAll() {
        return dao.findAll();
    }
}
