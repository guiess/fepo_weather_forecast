package com.weatherforecast.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class WeatherErrorHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleValidationExceptions(ConstraintViolationException e) {
        return getModel(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ModelAndView handleValidationExceptions(HttpClientErrorException e) {
        if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return getModel(HttpStatus.NOT_FOUND, e, "City not found");
        }
        else if(e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            return getModel(HttpStatus.UNAUTHORIZED, e, "Unauthorised error. Please check appId");
        }
        return getModel(HttpStatus.INTERNAL_SERVER_ERROR, e,
                "Something bad has happened when retrieving information from the weather service");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception e) {
        return getModel(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ModelAndView getModel(HttpStatus status, Exception e){
        return getModel(status, e, null);
    }

    private ModelAndView getModel(HttpStatus status, Exception e, String message){
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorCode", status);
        mav.addObject("message", message==null?e.getMessage():message);
        mav.addObject("stackTrace", e.getStackTrace());
        mav.setViewName("error");
        return mav;
    }
}