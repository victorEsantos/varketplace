package com.varketplace.infra.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError
{
    private static final long serialVersionUID = 5118442155284660506L;

    private List<FieldErrorMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path)
    {
        super(timestamp, status, error, message, path);
    }

    public List<FieldErrorMessage> getErrors()
    {
        return errors;
    }

    public void addError(String fieldName, String message)
    {
        this.errors.add(new FieldErrorMessage(fieldName, message));
    }
}