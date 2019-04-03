package etf.unsa.ba.user_management.validator;

import etf.unsa.ba.user_management.service.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private UserService userService;

    @Autowired
    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername constraint) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userService.getByUsername(username) == null;
    }
}