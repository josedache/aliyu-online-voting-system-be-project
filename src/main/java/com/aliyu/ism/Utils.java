package com.aliyu.ism;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Utils {

    public static boolean validationErrorCheck(BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println("error");
            int errorCount = validationResult.getFieldErrorCount();
            if (errorCount > 1) {
                FieldError fieldError = validationResult.getFieldError();
                System.out.format("%s: %s - %s",
                        fieldError.getField(), fieldError.getRejectedValue(), fieldError.getCode()
                );
            } else {
                for (FieldError fieldError : validationResult.getFieldErrors()) {
                    System.out.format("%s: %s - %s",
                            fieldError.getField(), fieldError.getRejectedValue(), fieldError.getCode()
                    );
                }
            }
            return true;
        }

        return false;
    }
}
