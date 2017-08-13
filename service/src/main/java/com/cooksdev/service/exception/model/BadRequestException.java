package com.cooksdev.service.exception.model;

import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.base.GenericException;

public class BadRequestException extends GenericException {

    public BadRequestException(ErrorReason errorReason, Object... reasonParam){
        super(errorReason,reasonParam);
    }
}