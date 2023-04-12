package com.example.demo;

public class InsufficientBalanceException extends Exception {

	private String message;

	public InsufficientBalanceException(String string) {
		message = string;
	}

	@Override
	public String toString() {
		return message;
	}

}
