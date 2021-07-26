package com.lowes.response.domains;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Quiz {

	@JsonProperty("category")
	public String category;
	@JsonProperty("results")
	public List<Result> results = null;

}