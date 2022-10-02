package com.phonepe.vehiclerenting.exception;

public class NotFoundBookingStrategy extends RuntimeException {
    public NotFoundBookingStrategy(String message) {
        super(message);
    }
}
