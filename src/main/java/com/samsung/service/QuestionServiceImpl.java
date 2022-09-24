package com.samsung.service;

import com.samsung.dao.QuestionDao;
import com.samsung.domain.Question;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao dao;

    @Override
    public List<Question> getAll() {
        return dao.findAll();
    }

    @Override
    public Question getById(int id) {
        return dao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public Question save(Question question) {
        return dao.save(question);
    }
    @Override
    public void changeLanguage(String pathToCsvFile){
        dao.changeLanguage(pathToCsvFile);
    }
}
