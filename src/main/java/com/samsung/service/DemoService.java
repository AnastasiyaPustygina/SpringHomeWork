package com.samsung.service;

import com.samsung.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Scanner;

@PropertySource("classpath:application.properties")
@Service
public class DemoService {

    @Value("${numberForPassExam}")
    private int idForPassExam;

    private final QuestionService questionService;

    public DemoService(QuestionService questionService) {
        this.questionService = questionService;
    }


    public void questionDemo(){
        int points = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first and last name, please");
        String name = scanner.nextLine();
        System.out.println("Ok, now you can answer the questions. Let's start!");
        List<Question> questionList = questionService.getAll();
        for (Question question : questionList) {
            System.out.println(question.getId()
                    + ". " + question.getText());
            String answer = scanner.nextLine();
            if(answer.equals(question.getAnswer())) points++;
        }
        System.out.println(points == 1 ? name + ", your result is " + points + " correct answer" :
                name + ", your result is " + points + " correct answers");
        System.out.println(points < idForPassExam ? "Unfortunately, you failed the test..." :
                "Congratulations! You passed the test!");
    }
}
