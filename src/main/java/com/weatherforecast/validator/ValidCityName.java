package com.weatherforecast.validator;

import com.weatherforecast.validator.impl.ValidCityNameImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = ValidCityNameImpl.class)
public @interface ValidCityName {
    String message() default "City name contains non-literal chars";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
