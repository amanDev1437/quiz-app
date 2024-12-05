package com.example.quizapp.controller;

import com.example.quizapp.models.QuestionWrapper;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.models.Response;
import com.example.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Quiz> create(@RequestParam String cat, @RequestParam int numQ, @RequestParam String title){

        Quiz quiz = quizService.createQuiz(cat,numQ,title);

        return new ResponseEntity<>(quiz, HttpStatus.CREATED);


    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){

        List<QuestionWrapper> questionWrappers = quizService.getQuiz(id);

        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);

    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Object> getScore(@PathVariable Integer id, @RequestBody List<Response> responses){

        Integer score = quizService.calculateResult(id,responses);

        return new ResponseEntity<>(score,HttpStatus.OK);

    }
}
