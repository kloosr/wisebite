package wisebite.wisebite.service.validation;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import wisebite.wisebite.service.AdminService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface ValidUsername {
    String message() default "Username already exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
class UsernameValidator implements
        ConstraintValidator<ValidUsername, String> {
    private AdminService adminService;
    @Override
    public void initialize(ValidUsername validUsername) {
        ConstraintValidator.super.initialize(validUsername);
    }

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext cxt) {
        return (!adminService.usernameExists(username));
    }

}