package com.electronic.store.validation;


import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AboutValidator.class)
public @interface AboutValid {

     //error message
    java.lang.String message() default "About section is blank !!";


    //group of constraints
    java.lang.Class<?>[] groups() default {};


    //additional information about annotation
    java.lang.Class<? extends jakarta.validation.Payload>[] payload() default {};

}
