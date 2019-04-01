package etf.unsa.ba.user_management.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {
    private static final int ADULT_AGE = 18;

    @Override
    public void initialize(Adult constraint) {
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        return dateOfBirth != null && LocalDate.now().minusYears(ADULT_AGE).isAfter(dateOfBirth);
    }
}
