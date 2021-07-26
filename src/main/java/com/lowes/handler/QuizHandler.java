package com.lowes.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import com.lowes.exception.LowesException;
import com.lowes.response.domains.Quiz;
import com.lowes.response.domains.Response;
import com.lowes.response.domains.Result;
import com.lowes.service.AsyncQuizService;
import com.lowes.service.ReactiveQuizService;
import com.lowes.service.domains.ServiceResponse;

import reactor.core.publisher.Mono;

@Component
public class QuizHandler {
	
	@Autowired
	AsyncQuizService asyncQuizService;
	
	@Autowired
	ReactiveQuizService reactiveQuizService;
	
	@Value("${external.url.1}")
	public String url1;
	
	@Value("${external.url.2}")
	public String url2;
	
	public Mono<Response> getQuizReactive() throws LowesException{
		Mono<ServiceResponse> filmresp = reactiveQuizService.getQuizReactive(url1);
		Mono<ServiceResponse> musicresp = reactiveQuizService.getQuizReactive(url2);
		return Mono.zip(filmresp, musicresp).flatMap(res ->(constructResponse(res.getT1(),res.getT2())) );
	}
	
	private Mono<Response> constructResponse(ServiceResponse t1, ServiceResponse t2) {
		Response response = new Response();
		List<Quiz> quizList = new ArrayList<Quiz>();
		try {
			Quiz filmQuiz = getQuizFromServiceResponse(t1);
			Quiz musicQuiz = getQuizFromServiceResponse(t2);
			quizList.add(filmQuiz);
			quizList.add(musicQuiz);
			response.setQuiz(quizList);
		}catch(LowesException e){
			response.getAdditionalProperties().put("errorMessage", e.getErrorMessage());
		}
		return Mono.just(response);
	}
	
	public Response getQuizAsync() throws LowesException, InterruptedException, ExecutionException {
		Response resp = new Response();
		CompletableFuture<ServiceResponse> filmResponseFuture = asyncQuizService.getQuizAsync(url1);
		CompletableFuture<ServiceResponse> musicResponseFuture = asyncQuizService.getQuizAsync(url2);
		List<Quiz> quizList = new ArrayList<Quiz>();
		try {
			ServiceResponse filmResponse = filmResponseFuture.get();
			ServiceResponse musicResponse = musicResponseFuture.get();
			Quiz filmQuiz = getQuizFromServiceResponse(filmResponse);
			Quiz musicQuiz = getQuizFromServiceResponse(musicResponse);
			quizList.add(musicQuiz);
			quizList.add(filmQuiz);
			resp.setQuiz(quizList);
		} catch (LowesException e) {
			resp.getAdditionalProperties().put("errorMessage", e.getMessage());
		}
		return resp;
	}

	private Quiz getQuizFromServiceResponse(ServiceResponse serviceResponse) throws LowesException {
		Quiz quiz = new Quiz();
		List<Result> resultList = new ArrayList<Result>();
		if(serviceResponse != null && serviceResponse.getResponseCode() == 0) {
			serviceResponse.results.forEach(x-> {
				Result result = new Result();
				quiz.setCategory(x.category);
				result.setDifficulty(x.difficulty);
				result.setQuestion(x.question);
				result.setCorrectAnswer(x.correctAnswer);
				result.setAllAnswers(x.incorrectAnswers);
				result.allAnswers.add(x.correctAnswer);
				Collections.shuffle(result.allAnswers);
				result.setType(x.type);
				resultList.add(result);
			});
		}else {
			throw new LowesException("ServiceResponse is either null or ResponseCode is not zero");
		}
		quiz.setResults(resultList);
		return quiz;
	}
}
