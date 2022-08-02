package com.samsung.domain;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class Question {

    private int number;
    private String text;
    private String answer;

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "number=" + number +
                ", text='" + text + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return number == question.number && Objects.equals(text, question.text) && Objects.equals(answer, question.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, text, answer);
    }
}
