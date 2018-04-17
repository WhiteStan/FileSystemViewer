package com.epam.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Specefied path is not exist")
public class InvalidFilesystemPathException extends RuntimeException {
	
	public InvalidFilesystemPathException() {
		super();
	}

	public InvalidFilesystemPathException(String s) {
		super(s);
	}

	public InvalidFilesystemPathException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public InvalidFilesystemPathException(Throwable throwable) {
		super(throwable);
	}
	
}
