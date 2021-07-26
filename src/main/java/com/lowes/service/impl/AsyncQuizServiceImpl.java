package com.lowes.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowes.exception.LowesException;
import com.lowes.service.AsyncQuizService;
import com.lowes.service.domains.ServiceResponse;

@Service
public class AsyncQuizServiceImpl implements AsyncQuizService{

	RestTemplate restTemplate = new RestTemplate();
	
	@Override
	@Async
	public CompletableFuture<ServiceResponse> getQuizAsync(String url) throws LowesException {
		try {
			ServiceResponse response = restTemplate.getForObject(url, ServiceResponse.class);
			return CompletableFuture.completedFuture(response);
		}catch(Exception e) {
			throw new LowesException(e.getMessage());
		}
	}
}
