package com.samsung.shell;

import com.samsung.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@PropertySource("classpath:application.yml")
@ShellComponent
@RequiredArgsConstructor
public class ExamCommands {

    private final DemoService demoService;
    private boolean isLanguageSelected;
    private boolean isLogged;
    private boolean isTestCompleted;

    @ShellMethod(value = "language command", key = {"l", "language"})
    public void selectLanguage() {
        demoService.selectLanguage();
        isLanguageSelected = true;
    }
    @ShellMethod(value = "login command", key = {"n", "name", "login"})
    public void login() {
        demoService.login();
        isLogged = true;
    }
    @ShellMethod(value = "test command", key = {"t", "test"})
    @ShellMethodAvailability(value = "isNameEntered")
    public void test() {
        resetResult();
        demoService.testStudent();
        isTestCompleted = true;
    }
    @ShellMethod(value = "result command", key = {"r", "result"})
    @ShellMethodAvailability(value = "isTestCompleted")
    public void showResult() {
        demoService.showResult();
    }
    private void resetResult(){
        isTestCompleted = false;
        demoService.resetResult();
    }
    private Availability isTestCompleted() {
        return isTestCompleted ? Availability.available():
                Availability.unavailable("For the beginning you need to start the test");
    }
    private Availability isNameEntered() {
        return isLogged ? Availability.available() :
                Availability.unavailable("For the beginning you need to write your name");
    }
    private Availability isLanguageSelected() {
        return isLanguageSelected ? Availability.available() :
                Availability.unavailable("For the beginning you need to select the language");
    }
}
