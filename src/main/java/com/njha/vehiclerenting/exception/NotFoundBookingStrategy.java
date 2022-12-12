package com.njha.vehiclerenting.exception;

public class NotFoundBookingStrategy extends RuntimeException {
    public NotFoundBookingStrategy(String message) {
        super(message);
    }
}
