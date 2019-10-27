package com.components.exception;

public class CarNotFoundException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Car with such credentials was not found!";
    }
}
