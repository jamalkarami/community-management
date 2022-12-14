package com.isicod.net.communitiesManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class FailedAuthentication extends RuntimeException{

    public FailedAuthentication(String message) {
        super(message);
    }
}
