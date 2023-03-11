package com.bitwisemj.jobsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationException extends HttpServerErrorException {

    public ApplicationException(final HttpStatus status, final String message) {
        super(status, message);
    }
}
