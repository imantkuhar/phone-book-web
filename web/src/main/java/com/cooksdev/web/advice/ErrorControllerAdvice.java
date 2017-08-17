package com.cooksdev.web.advice;

import com.cooksdev.service.exception.model.BadRequestException;
import com.cooksdev.service.exception.model.InternalServerErrorException;
import com.cooksdev.service.exception.model.JsonExceptionInfo;
import com.cooksdev.service.exception.model.NotFoundException;
import com.cooksdev.service.exception.model.base.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    JsonExceptionInfo handleNotFoundException(NotFoundException e) {
        return print(e);
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    JsonExceptionInfo handleBadRequestException(BadRequestException e) {
        return print(e);
    }

    @ExceptionHandler({InternalServerErrorException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    JsonExceptionInfo handleInternalServerError(InternalServerErrorException e) {
        return print(e);
    }

    private JsonExceptionInfo print(GenericException e) {
        return new JsonExceptionInfo(e);
    }
}
