package wisebite.wisebite.controller.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HeightValidator.class)
public @interface ValidHeight {
    String message() default "Height should be between 100 and 250.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
class HeightValidator implements
        ConstraintValidator<ValidHeight, Integer> {

    @Override
    public void initialize(ValidHeight validWeight) {
    }

    @Override
    public boolean isValid(Integer height,
                           ConstraintValidatorContext cxt) {
        return (height > 100 && height < 250);
    }

}