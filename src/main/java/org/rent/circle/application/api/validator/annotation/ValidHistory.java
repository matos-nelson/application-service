package org.rent.circle.application.api.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.rent.circle.application.api.validator.ResidentialHistoryValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.FIELD
})
@Constraint(validatedBy = ResidentialHistoryValidator.class)
public @interface ValidHistory {

    String message() default "Must Provide From Dates That Occur Before To Dates";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
