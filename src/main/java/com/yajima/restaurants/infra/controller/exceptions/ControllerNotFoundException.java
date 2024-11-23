package com.yajima.restaurants.infra.controller.exceptions;

public class ControllerNotFoundException extends RuntimeException{
    public ControllerNotFoundException(String message){ super(message);}
}