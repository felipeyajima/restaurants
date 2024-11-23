package com.yajima.restaurants.infra.controller.exceptions;

public class ControllerSystemException extends RuntimeException{
    public ControllerSystemException(String message){ super(message);}
}