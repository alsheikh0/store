package com.codewithmosh.store.exceptions;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String s) {
        super(s);
    }
}
