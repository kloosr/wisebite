package wisebite.wisebite.service.validation;

import jakarta.validation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface ValidPassword {
    String message() default "Password may not contain blank spaces.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
class PasswordValidator implements
        ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword validPassword) {
        ConstraintValidator.super.initialize(validPassword);
    }

    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext context) {
        return (!password.contains(" "));
    }

}