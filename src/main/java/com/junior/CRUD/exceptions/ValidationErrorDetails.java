package com.junior.CRUD.exceptions;

import java.util.Map;

public class ValidationErrorDetails extends ErrorDetails{
    private Map message;

    public Map getMessage() {
        return message;
    }

    public void setMessage(Map message) {
        this.message = message;
    }
}
