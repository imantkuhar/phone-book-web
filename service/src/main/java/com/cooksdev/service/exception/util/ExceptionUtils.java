package com.cooksdev.service.exception.util;

import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

public class ExceptionUtils{

    public static String getFullMessage(Throwable th) {
        String message;
        if (th instanceof NullPointerException) {
            message = "NullPointerException";
        } else {
            message = getMessage(th);
        }

        String rootMessage = getRootCauseMessage(th);

        if (StringUtils.isEmpty(rootMessage)) {
            return message;
        } else if (StringUtils.isEmpty(message)) {
            return rootMessage;
        } else if (message.equals(rootMessage)) {
            return message;
        }
        return message + ". " + rootMessage;
    }

    private static String getMessage(Throwable th) {
        if (th == null) {
            return StringUtils.EMPTY;
        }
        return StringUtils.trimToEmpty(th.getMessage());
    }
}
