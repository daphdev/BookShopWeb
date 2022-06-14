package com.bookshopweb.dto;

import java.util.StringJoiner;

public class SuccessMessage {
    private final int statusCode;
    private final String message;

    public SuccessMessage(int statusCode,
                          String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SuccessMessage.class.getSimpleName() + "[", "]")
                .add("statusCode=" + statusCode)
                .add("message='" + message + "'")
                .toString();
    }
}
