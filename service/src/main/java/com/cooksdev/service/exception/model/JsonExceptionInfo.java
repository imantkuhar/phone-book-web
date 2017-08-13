package com.cooksdev.service.exception.model;

import com.cooksdev.service.exception.model.base.GenericException;
import com.cooksdev.service.exception.util.DateTimeToJsonIso;
import com.cooksdev.service.exception.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude
public class JsonExceptionInfo {
    @JsonSerialize(using = DateTimeToJsonIso.class)
    private final LocalDateTime timestamp = DateUtils.nowUtc();
    private final String error;
    private final String message;
    private final Object details;

    public JsonExceptionInfo(GenericException e) {
        error = e.getErrorReason().name();
        message = e.getFullMessage();
        details = e.errorDetails;
    }
}
