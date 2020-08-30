package com.lucas.gowatch.controller.exception;

public class VideoCreateException extends Exception {
    public VideoCreateException(String message, Exception e) {
        super(message, e);
    }

    public VideoCreateException(Exception e) {
        super(e);
    }
}
