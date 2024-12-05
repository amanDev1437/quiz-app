package com.example.quizapp.controller;


import com.example.quizapp.models.Question;
import com.example.quizapp.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/question")
    public ResponseEntity<List<Question>> saveQuestion(@RequestBody List<Question> question) {
        System.out.println(question);
        List<Question> savedQuestion = questionService.saveQuestion(question);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        List<Question> questionList = questionService.getAllQuestion();
        return new ResponseEntity<>(questionList,HttpStatus.OK);
    }

    @GetMapping("/question/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
        List<Question> questionList = questionService.getByCategory(category);
        return new ResponseEntity<>(questionList,HttpStatus.OK);
    }



}
