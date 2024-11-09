package com.yajima.restaurants.controller.exception;

public class ControllerNotFoundException extends  RuntimeException{
    public ControllerNotFoundException(String message){
        super(message);
    }
}
