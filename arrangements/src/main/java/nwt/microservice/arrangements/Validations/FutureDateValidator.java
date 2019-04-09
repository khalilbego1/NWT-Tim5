package nwt.microservice.arrangements.Validations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component
public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {

    @Override
    public void initialize(FutureDate constraint) {
    }

    @Override
    public boolean isValid(LocalDate enteredDate, ConstraintValidatorContext constraintValidatorContext) {
        return enteredDate != null && LocalDate.now().isBefore(enteredDate);
    }
}
