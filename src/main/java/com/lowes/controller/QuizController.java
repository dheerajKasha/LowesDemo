package com.lowes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowes.handler.QuizHandler;
import com.lowes.response.domains.Response;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coding/exercise")
public class QuizController {

	@Autowired
	QuizHandler quizHandler;
	
    @GetMapping("/quizReactive")
	public Mono<Response> getQuiz() throws Exception {
		return quizHandler.getQuizReactive();
	}
	
    @GetMapping("/quizAsync")
	public Response getQuizAsync() throws Exception{
		return quizHandler.getQuizAsync();
	}
}
