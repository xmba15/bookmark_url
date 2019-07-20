package com.bookmarkapp.bookmark_url.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubTagNotFoundException extends RuntimeException {
    public SubTagNotFoundException(Integer id) {
        super("SubTag id not found: " + id);
    }
}
