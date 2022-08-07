package com.samsung.dao;

import com.samsung.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoSimple implements QuestionDao {

    private Cache<Question> cache;

    private final String pathToCsvFile;

    public QuestionDaoSimple(@Value("${pathToCsvFile}") String pathToCsvFile) {
        this.pathToCsvFile = pathToCsvFile;
        cache = new Cache<>();
    }

    @Override
    public List<Question> findAll() {
        if(cache.getList() != null) return cache.getList();
        ArrayList<Question> questions = new ArrayList<>();
        questions = new ArrayList<>();
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(
                    pathToCsvFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String row = null;
        while (true) {
            try {
                if ((row = csvReader.readLine()) == null)
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] data = row.split(";");
            questions.add(new Question(Integer.parseInt(data[0]), data[1], data[2]));
        }
        try {
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cache.setList(questions);
        return questions;
    }
}
