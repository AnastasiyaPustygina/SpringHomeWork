package com.samsung.domain;

import lombok.AllArgsConstructor;
import lombok.Value;


@AllArgsConstructor
@Value
public class Question {
    int id;
    String text;
    String answer;
}
