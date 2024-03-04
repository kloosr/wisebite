package wisebite.wisebite.service.validation;

import jakarta.validation.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HeightValidator.class)
public @interface ValidHeight {
    String message() default "Invalid height.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
class HeightValidator implements
        ConstraintValidator<ValidHeight, Integer> {
    private final Integer MIN_HEIGHT = 100;
    private final Integer MAX_HEIGHT = 250;
    @Override
    public void initialize(ValidHeight validHeight) {
        ConstraintValidator.super.initialize(validHeight);
    }

    @Override
    public boolean isValid(Integer height,
                           ConstraintValidatorContext context) {
        if (height != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format(String.format("Height should be between %d and %d.", MIN_HEIGHT, MAX_HEIGHT)))
                    .addConstraintViolation();
            return (height > MIN_HEIGHT && height < MAX_HEIGHT);
        } else {
            return false;
        }
    }
}