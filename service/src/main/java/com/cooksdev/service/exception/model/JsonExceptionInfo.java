package com.cooksdev.service.exception.model;

import com.cooksdev.service.exception.model.base.GenericException;
import com.cooksdev.service.util.DateTimeToJsonIso;
import com.cooksdev.service.util.DateUtils;
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
    private final String description;
    private final Object details;

    public JsonExceptionInfo(GenericException e) {
        error = e.getErrorReason().name();
        message = e.getFullMessage();
        description = e.getErrorReason().getDescription();
        details = e.errorDetails;
    }
}
