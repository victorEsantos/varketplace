package com.varketplace.infra.exception;

import java.io.Serializable;

public class FieldErrorMessage implements Serializable
{
	private static final long serialVersionUID = 3487712548070655786L;

	private String fieldName;
	private String message;

	public FieldErrorMessage()
	{

	}

	public FieldErrorMessage(String fieldName, String message)
	{
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}