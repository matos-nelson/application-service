package org.rent.circle.application.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import org.rent.circle.application.api.dto.ResidentialHistoryDto;
import org.rent.circle.application.api.validator.annotation.ValidHistory;

public class ResidentialHistoryValidator implements ConstraintValidator<ValidHistory, ResidentialHistoryDto> {

    @Override
    public boolean isValid(ResidentialHistoryDto value, ConstraintValidatorContext context) {

        LocalDate fromDate = LocalDate.of(value.getResidedFromYear(), value.getResidedFromMonth(), 1);
        LocalDate toDate = LocalDate.of(value.getResidedToYear(), value.getResidedToMonth(), 1);

        return fromDate.isBefore(toDate);
    }
}
