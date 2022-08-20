package com.samsung.shell;

import com.samsung.domain.Question;
import com.samsung.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Scanner;

@PropertySource("classpath:application.yml")
@ShellComponent
@RequiredArgsConstructor
public class ExamCommands {

    private final QuestionService questionService;
    private final Scanner scanner = new Scanner(System.in);
    private String name;
    private int points;
    private boolean isTestCompleted;
    @Value("${numberForPassExam}")
    private int numberForPassExam;

    @ShellMethod(value = "login command", key = {"n", "name"})
    public String login() {
        System.out.println("Enter your first and last name, please");
        name = scanner.nextLine();
        return "Ok, now you can answer the questions. Let's start!";
    }
    @ShellMethod(value = "test command", key = {"t", "test"})
    @ShellMethodAvailability(value = "isNameEntered")
    public String test() {
        List<Question> questionList = questionService.getAll();
        questionList.forEach(q -> {
            System.out.println(q.getId() + ". " + q.getText());
            if(q.getAnswer().equals(scanner.nextLine())){
                points++;
            }
        });
        isTestCompleted = true;
        return "Ok, you completed the test";
    }
    @ShellMethod(value = "result command", key = {"r", "result"})
    @ShellMethodAvailability(value = "isTestCompleted")
    public void getResult() {
        System.out.print(name + ", your result is " + points + " ");
        System.out.println(points == 1 ? " correct answer" : "correct answers");
        System.out.println( points < numberForPassExam ? "Unfortunately, you failed the test...":
                "Congratulations! You passed the test!");
        points = 0;
    }
    private Availability isTestCompleted() {
        return isTestCompleted ? Availability.available():
                Availability.unavailable("For the beginning you need to start the test");
    }
    private Availability isNameEntered() {
        return name == null ? Availability.unavailable("For the beginning you need to write your name"):
                Availability.available();
    }
}
