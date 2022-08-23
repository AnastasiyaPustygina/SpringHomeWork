package com.samsung.service;

import com.samsung.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@PropertySource("classpath:application.yml")
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DemoService {

    private final QuestionService questionService;
    private final LocalizedMessage localizedMessage;
    @Value("${numberForPassExam}")
    private final int numberForPassExam;
    private int points;
    private String name;
    private final Scanner scanner = new Scanner(System.in);

    public void questionDemo(){
        selectLanguage();
        login();
        testStudent();
        showResult();
    }

    public void selectLanguage(){
        while (true) {
            System.out.println("Select the language - write RU or EN");
            String language = scanner.nextLine();
            if(language.equals("RU") || language.equals("EN")) {
                localizedMessage.selectLanguage(language.equals("RU") ? "ru-RU" : "");
                questionService.changeLanguage(localizedMessage.getMessage("path-to-csv-file"));
                break;
            }
        }
    }

    public void login(){
        System.out.println(localizedMessage.getMessage("string.greeting-edit-name"));
        name = scanner.nextLine();
        System.out.println(localizedMessage.getMessage("string.greeting-lets-start"));
    }
    public void testStudent(){
        System.out.println(localizedMessage.getMessage("string.greeting-lets-start"));
        List<Question> questionList = questionService.getAll();
        questionList.forEach(q -> {
            System.out.println(q.getId() + ". " + q.getText());
            if(q.getAnswer().equals(scanner.nextLine())){
                points++;
            }
        });
    }

    public void showResult(){
        System.out.print(name + localizedMessage.getMessage("string.result1") + points + " ");
        switch (points) {
            case 1:
                System.out.println(localizedMessage.getMessage("string.resultWithOnePoint2"));
                break;
            case 0:
            case 5:
                System.out.println(localizedMessage.getMessage("string.resultWithZeroOrFivePoint2"));
                break;
            default:
                System.out.println(localizedMessage.getMessage("string.resultWithOtherPoint2"));
        }
        System.out.println(points < numberForPassExam ? localizedMessage.getMessage("string.FallTest"):
                localizedMessage.getMessage("string.PassTest"));
    }

    public void resetResult(){
        points = 0;
    }

}
