package com.samsung.service;

import com.samsung.dao.QuestionDaoSimple;
import com.samsung.domain.Question;
import com.samsung.service.QuestionServiceImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class QuestionServiceTest {
    @Test
    public void findAllTest(){
        try {
            String csv = "dataFindAllTest.csv";
            PrintWriter writer = new PrintWriter(new FileWriter(csv));
            String csvParams = "1;How much is 5 * 5?;25\n" +
                    "2;How much is 4 + 3?;7\n" +
                    "3;How much is 1 - 1?;0";
            writer.write(csvParams);
            writer.close();
            QuestionDaoSimple questionDaoSimple =
                    new QuestionDaoSimple("dataFindAllTest.csv");
            QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl(questionDaoSimple);
            ArrayList<Question> questions = new ArrayList<>() {
            };
            questions.add(new Question(1, "How much is 5 * 5?", "25"));
            questions.add(new Question(2, "How much is 4 + 3?", "7"));
            questions.add(new Question(3, "How much is 1 - 1?", "0"));

            ArrayList<Question> questionsFromMethod =
                    (ArrayList<Question>) questionServiceImpl.getAll();
            File file = new File("dataFindAllTest.csv");
            file.delete();
            assert(questions.equals(questionsFromMethod));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
