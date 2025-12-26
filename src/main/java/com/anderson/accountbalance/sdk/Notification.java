package com.anderson.accountbalance.sdk;

import java.util.ArrayList;
import java.util.List;

public class Notification<T> {

    private T result;
    private List<String> errors = new ArrayList<>();

    public void addError(String message) {
        errors.add(message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}

