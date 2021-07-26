package com.lowes.service.domains;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ServiceResponse {

	@JsonProperty("response_code")
	public Integer responseCode;
	@JsonProperty("results")
	public List<ServiceResponseResult> results = null;

}