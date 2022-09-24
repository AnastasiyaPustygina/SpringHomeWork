package com.samsung.dao;


import com.samsung.domain.Question;
import com.samsung.exception.QuestionNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Класс QuestionDao")
@SpringBootTest
@ActiveProfiles("test")
public class QuestionDaoTest {

    private final String csv = "dataFindAllTest.csv";

    private QuestionDaoSimple dao;

    @BeforeEach
    public void setUp(){
        dao = new QuestionDaoSimple(csv);
    }

    @DisplayName("Должен возвращать все вопросы")
    @Test
    public void shouldFindAllQuestions(){
        try {
        if(!new File(csv).exists()) new File(csv).createNewFile();
            PrintWriter writer = new PrintWriter(new FileWriter(csv));
            String csvParams = "1;How much is 5 * 5?;25\n" +
                    "2;How much is 4 + 3?;7\n" +
                    "3;How much is 1 - 1?;0";
            writer.write(csvParams);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question(1, "How much is 5 * 5?", "25"));
        questionList.add(new Question(2, "How much is 4 + 3?", "7"));
        questionList.add(new Question(3, "How much is 1 - 1?", "0"));
        assertEquals(dao.findAll(), questionList);
    }

    @DisplayName("Должен возвращать вопрос по id")
    @Test
    public void shouldFindQuestionById(){
        Question question = new Question(1, "How much is 5 * 5?", "25");
        assertEquals(question, dao.findById(1));
    }

    @DisplayName("Должен добавлять вопрос")
    @Test
    public void shouldAddQuestion(){
        int sizeBefore = dao.findAll().size();
        Question question = new Question(4, "How much is 5 * 9?", "45");
        Question daoQuestion =  dao.save(question);
        assertEquals(sizeBefore + 1, dao.findAll().size());
        assertEquals(daoQuestion, question);
    }

    @DisplayName("Должен удалять вопрос по id")
    @Test
    public void shouldDeleteQuestionById(){
        dao.deleteById(1);
        assertThrows(QuestionNotFoundException.class, () -> dao.findById(1));
    }
}
