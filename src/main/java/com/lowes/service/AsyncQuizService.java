package com.lowes.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.lowes.exception.LowesException;
import com.lowes.service.domains.ServiceResponse;


@Service
public interface AsyncQuizService {

	public CompletableFuture<ServiceResponse> getQuizAsync(String url) throws LowesException;
	
}
