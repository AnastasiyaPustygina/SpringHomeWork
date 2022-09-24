package com.samsung.service;

import com.samsung.dao.QuestionDao;
import com.samsung.domain.Question;
import com.samsung.exception.QuestionNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@DisplayName("Класс QuestionService")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class QuestionServiceTest {
    @Mock
    private QuestionDao questionDao;
    @InjectMocks
    private QuestionServiceImpl service;

    private List<Question> questions;

    @BeforeEach
    public void setUp(){
        service = new QuestionServiceImpl(questionDao);
        questions = new ArrayList<>();
        questions.add(new Question(1, "How much is 5 * 5?", "25"));
        questions.add(new Question(2, "How much is 4 + 3?", "7"));
        questions.add(new Question(3, "How much is 1 - 1?", "0"));
    }

    @DisplayName("должен возвращать все вопросы")
    @Test
    public void shouldFindAllQuestions(){
        given(questionDao.findAll()).willReturn(questions);
        assertEquals(questions, service.getAll());
    }

    @DisplayName("должен находить по id")
    @Test
    public void shouldFindQuestionById(){
        Question question = questions.get(1);
        given(questionDao.findById(eq(1))).willReturn(
                question);
        assertEquals(question, service.getById(1));
    }

    @DisplayName("должен удалять по id")
    @Test
    public void shouldDeleteQuestionById(){
        doThrow(new QuestionNotFoundException("Question with id " + 1 + " not found")).when(
                questionDao).deleteById(1);
        assertThrows(QuestionNotFoundException.class, () -> service.deleteById(1));
    }

    @DisplayName("должен добавлять вопрос")
    @Test
    public void shouldAddQuestion(){
        given(questionDao.save(any())).willReturn(questions.get(1));
        Question serviceQuestion = service.save(questions.get(1));
        assertEquals(serviceQuestion, questions.get(1));
    }
}
