package com.dnikitin.javaspringboottest2.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ErrorMessage(
        String message,
        String detailedMessage,
        LocalDateTime errorTime

) {
}
