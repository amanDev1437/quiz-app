package com.example.quizapp.services;


import com.example.quizapp.models.Question;
import com.example.quizapp.repository.QuestionRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepo questionRepo;

    public QuestionService(QuestionRepo questionRepo){
        this.questionRepo = questionRepo;
    }

    public List<Question> saveQuestion(List<Question> question){
        System.out.println(question.get(1).getQuestionTitle());
        return  questionRepo.saveAll(question);
    }

    public List<Question> getAllQuestion(){
        return questionRepo.findAll();
    }

    public List<Question> getByCategory(String category){
        System.out.println(category);
        return questionRepo.findByCategory(category);
    }

}
