package com.weatherforecast.validator.impl;

import com.weatherforecast.validator.ValidCityName;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidCityNameImpl implements ConstraintValidator<ValidCityName, String> {

    @Override
    public boolean isValid(String city, ConstraintValidatorContext constraintValidatorContext){
        return city.matches("[a-zA-Z]+");
    }
}
