package com.samsung.service;

import com.samsung.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@PropertySource("classpath:application.yml")
@Service
@RequiredArgsConstructor
public class DemoService {

    @Value("${numberForPassExam}")
    private int numberForPassExam;
    @Autowired
    private Environment environment;
    private int points;
    private final MessageSource messageSource;
    private final QuestionService questionService;
    private String languageTag;

    public void questionDemo(){
        points = 0;
        Scanner scanner = new Scanner(System.in);
        while (languageTag == null) {
            System.out.println("Select the language - write RU or EN");
            String language = scanner.nextLine();
            if(language.equals("RU")) {
                languageTag = "ru-RU";
                questionService.changeLanguage(environment.getProperty("path-to-csv-file"));
            }
            else{
                if(language.equals("EN")){
                    languageTag = "en-US";
                }
            }
        }
        System.out.println(getMessage("string.greeting-edit-name"));
        String name = scanner.nextLine();
        System.out.println(getMessage("string.greeting-lets-start"));
        List<Question> questionList = questionService.getAll();
        questionList.forEach(q -> {
            System.out.println(q.getId() + ". " + q.getText());
            if(q.getAnswer().equals(scanner.nextLine())){
                points++;
            }
        });
        System.out.print(name + getMessage("string.result1") + points + " ");
        if(languageTag.equals("ru-RU")) {
            switch (points) {
                case 1:
                    System.out.println(getMessage("string.resultWithOnePoint2"));
                    break;
                case 0:
                case 5:
                    System.out.println(getMessage("string.resultWithZeroOrFivePoint2"));
                    break;
                default:
                    System.out.println(getMessage("string.resultWithOtherPoint2"));
            }
        }
        else{
            System.out.println(points == 1 ? getMessage(getMessage(
                    "string.resultWithOnePoint2")) : getMessage("string.result2"));
        }
        System.out.println(points < numberForPassExam ? getMessage("string.FallTest"):
                getMessage("string.PassTest"));
    }
    private String getMessage(String nameOfVariable){
        return messageSource.getMessage(nameOfVariable,
                null, Locale.forLanguageTag(languageTag));
    }
}
