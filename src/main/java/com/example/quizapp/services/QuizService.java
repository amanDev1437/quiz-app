package com.example.quizapp.services;

import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuestionWrapper;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.models.Response;
import com.example.quizapp.repository.QuestionRepo;
import com.example.quizapp.repository.QuizRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuestionRepo questionRepo;

    private final QuizRepo quizRepo;

    public QuizService(QuestionRepo questionRepo,QuizRepo quizRepo){
        this.questionRepo=questionRepo;
        this.quizRepo=quizRepo;
    }

    public Quiz createQuiz(String cat, int numQ, String title){
        List<Question> questionList = questionRepo.findByCategory(cat);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList.stream().limit(numQ).toList());
        return quizRepo.save(quiz);
    }

    public List<QuestionWrapper> getQuiz(int id){

        Optional<Quiz> quiz = quizRepo.findById(id);

        List<Question> questionList = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for(Question q: questionList){
            QuestionWrapper qw = new QuestionWrapper();
            qw.setId(q.getId());
            qw.setQuestionTitle(q.getQuestionTitle());
            qw.setOption1(q.getOption1());
            qw.setOption2(q.getOption2());
            qw.setOption3(q.getOption3());
            qw.setOption4(q.getOption4());

            questionWrappers.add(qw);
        }

        return questionWrappers;

    }

    public Integer calculateResult(Integer id, List<Response> responses) {

        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionList = quiz.get().getQuestions();
        int score = 0;
        int i = 0;

        for(Response r: responses){
            if(r.getUserAnswer().equals(questionList.get(i).getRightAnswer())){
                score++;
            }
            i++;
        }

        return score;
    }
}
