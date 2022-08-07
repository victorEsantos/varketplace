package com.varketplace.infra.exception;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -4677947446311808644L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}