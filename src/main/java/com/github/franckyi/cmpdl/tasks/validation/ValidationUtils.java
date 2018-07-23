package com.github.franckyi.cmpdl.tasks.validation;

import com.github.franckyi.cmpdl.CMPDL;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

public class ValidationUtils {

    public static RequiredFieldValidator requiredFieldValidator() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("This field is required.");
        validator.setIcon(CMPDL.ICON_ERROR);
        return validator;
    }

    public static NumberValidator numberValidator() {
        NumberValidator validator = new NumberValidator();
        validator.setMessage("Please enter a number.");
        validator.setIcon(CMPDL.ICON_ERROR);
        return validator;
    }
}
