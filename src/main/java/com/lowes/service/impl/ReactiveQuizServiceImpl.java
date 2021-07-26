package com.lowes.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowes.exception.LowesException;
import com.lowes.service.ReactiveQuizService;
import com.lowes.service.domains.ServiceResponse;

import reactor.core.publisher.Mono;

@Service
public class ReactiveQuizServiceImpl implements ReactiveQuizService{

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public Mono<ServiceResponse> getQuizReactive(String url) throws LowesException{
		try {
			ServiceResponse response = restTemplate.getForObject(url, ServiceResponse.class);
			return Mono.just(response);
		}catch(Exception e) {
			throw new LowesException(e.getMessage());
		}
		
	}

}
