package project.com.Security;

import project.com.Entity.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 */
public class PasswordValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    /**
     * @param constraintAnnotation
     */
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    /**
     * @param obj
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
