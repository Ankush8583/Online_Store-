package com.electronic.store.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AboutValidator implements ConstraintValidator<AboutValid,String> {


    //  private Logger logger = (Logger) LoggerFactory.getLogger(aboutValidator.class);

      private Logger logger = LoggerFactory.getLogger(AboutValidator.class);
    @Override


    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

         //logic

         logger.info("message from isValid : {}", s);

           if (s.isBlank()){
               return false;
           }else {
               return true;
           }

    }
}
