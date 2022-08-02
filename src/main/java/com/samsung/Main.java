package com.samsung;

import com.samsung.domain.Question;
import com.samsung.service.DemoService;
import com.samsung.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        new DemoService(context.getBean(QuestionService.class)).questionDemo();
    }
}
