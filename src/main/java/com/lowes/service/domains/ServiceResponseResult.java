package com.lowes.service.domains;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ServiceResponseResult {

	@JsonProperty("category")
	public String category;
	@JsonProperty("type")
	public String type;
	@JsonProperty("difficulty")
	public String difficulty;
	@JsonProperty("question")
	public String question;
	@JsonProperty("correct_answer")
	public String correctAnswer;
	@JsonProperty("incorrect_answers")
	public List<String> incorrectAnswers = null;

}