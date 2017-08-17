package com.cooksdev.service.exception.model;

import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.base.GenericException;

public class InternalServerErrorException extends GenericException {

    public InternalServerErrorException(ErrorReason errorReason, Object... reasonParam) {
        super(errorReason, reasonParam);
    }
}
