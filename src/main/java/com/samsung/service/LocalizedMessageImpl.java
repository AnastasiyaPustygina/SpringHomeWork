package com.samsung.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class LocalizedMessageImpl implements LocalizedMessage{

    private String languageTag;
    private final MessageSource messageSource;

    @Override
    public void selectLanguage(String languageTag){
        this.languageTag = languageTag;
    }
    @Override
    public String getMessage(String nameOfVariable) {
        return messageSource.getMessage(nameOfVariable,
                null, Locale.forLanguageTag(languageTag));
    }

}
