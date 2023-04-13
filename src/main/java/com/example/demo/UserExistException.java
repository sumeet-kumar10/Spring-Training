package com.example.demo;

public class UserExistException extends Exception {

	private String message;

	public UserExistException(String string) {
		this.message = string;
	}

	@Override
	public String toString() {
		return message;
	}

}
