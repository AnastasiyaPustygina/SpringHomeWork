package com.samsung.service;

public interface LocalizedMessage {
    String getMessage(String nameOfVariable);
    void selectLanguage(String languageTag);
}
