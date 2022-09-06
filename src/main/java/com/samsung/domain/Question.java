package com.samsung.domain;

import lombok.Value;


@Value
public class Question {
    int id;
    String text;
    String answer;
}
