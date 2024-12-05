package com.example.quizapp.repository;

import com.example.quizapp.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
