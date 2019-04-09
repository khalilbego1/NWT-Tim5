package nwt.microservice.arrangements.Validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, PARAMETER})
@Constraint(validatedBy = FutureDateValidator.class)
public @interface FutureDate {
    String message() default "Date must be set in the future!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
