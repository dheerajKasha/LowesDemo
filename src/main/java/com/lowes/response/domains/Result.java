package com.lowes.response.domains;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Result {

	@JsonProperty("type")
	public String type;
	@JsonProperty("difficulty")
	public String difficulty;
	@JsonProperty("question")
	public String question;
	@JsonProperty("all_answers")
	public List<String> allAnswers = null;
	@JsonProperty("correct_answer")
	public String correctAnswer;

}