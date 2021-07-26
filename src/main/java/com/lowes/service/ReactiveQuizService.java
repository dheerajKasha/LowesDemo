package com.lowes.service;


import org.springframework.stereotype.Service;

import com.lowes.exception.LowesException;
import com.lowes.service.domains.ServiceResponse;

import reactor.core.publisher.Mono;


@Service
public interface ReactiveQuizService {

	public Mono<ServiceResponse> getQuizReactive(String url) throws LowesException;
	
}
