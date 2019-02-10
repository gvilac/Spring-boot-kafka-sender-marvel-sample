package com.guillem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by guillem on 10/02/2019.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MapperException extends Exception {

    public MapperException(Throwable cause) {
        super(cause);
    }
}
