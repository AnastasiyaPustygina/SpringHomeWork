package com.samsung.service;

import com.samsung.domain.Question;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor

public class DemoService {

    private final QuestionService questionService;

    public void questionDemo(){
        List<Question> questionList = questionService.getAll();
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).getNumber()
                    + ". " + questionList.get(i).getText());
        }
    }
}
