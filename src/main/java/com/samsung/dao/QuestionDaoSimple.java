package com.samsung.dao;

import com.samsung.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@PropertySource({"classpath:messages_en_US.properties"})
@Repository
public class QuestionDaoSimple implements QuestionDao {

    private final Cache<Question> cache;

    private String pathToCsvFile;

    public QuestionDaoSimple(@Value("${path-to-csv-file}") String pathToCsvFile) {
        this.pathToCsvFile = pathToCsvFile;
        cache = new Cache<Question>();
    }

    @Override
    public List<Question> findAll() {
        if(cache.getList() != null) return cache.getList();
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
            cache.addToList(new Question(Integer.parseInt(
                    data[0]), data[1], data[2]));
        }
        try {
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cache.getList();
    }

    @Override
    public Question findById(int id){
        List<Question> questions = findAll();
        return questions.stream().filter(q -> q.getId() == id).collect(
                Collectors.toList()).get(0);
    }

    @Override
    public void deleteById(int id){
        List<Question> list = cache.getList();
        list.remove(findById(id));
        cache.setList(list);
    }

    @Override
    public void save(Question question) {
        cache.addToList(question);
    }

    @Override
    public void changeLanguage(String pathToCsvFile){
        this.pathToCsvFile = pathToCsvFile;
    }
}