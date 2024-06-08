package com.example.validation;

import com.example.exception.ValidationException;

public class Validator {

    public static void validateInputParameters(int rentalDays, int discountPercent) throws Exception {
        if(rentalDays < 1) throw new ValidationException("Rental day count must be at least one day or more");
        if(discountPercent < 0 || discountPercent > 100) throw new ValidationException("discount percent must be in the range of 0-100");
    }
}
