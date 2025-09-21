package com.jsp.springbootproject.SupplyChainManagement.exception;

public class IdNotFoundException extends RuntimeException{
	public IdNotFoundException(String message) {
		super(message);
	}
}