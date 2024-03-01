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
@Constraint(validatedBy = WeightValidator.class)
public @interface ValidWeight {
    String message() default "Weight should be between 40 and 200.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
class WeightValidator implements
        ConstraintValidator<ValidWeight, Double> {

    @Override
    public void initialize(ValidWeight validWeight) {
    }

    @Override
    public boolean isValid(Double weight,
                           ConstraintValidatorContext cxt) {
        return (weight > 40 && weight < 200);
    }

}