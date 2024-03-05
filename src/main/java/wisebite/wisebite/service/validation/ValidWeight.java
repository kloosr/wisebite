package wisebite.wisebite.service.validation;

import jakarta.validation.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WeightValidator.class)
public @interface ValidWeight {
    String message() default "Weight should be between 40 and 200.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
class WeightValidator implements
        ConstraintValidator<ValidWeight, Integer> {
    private final Integer MIN_WEIGHT = 40;
    private final Integer MAX_WEIGHT = 300;
    private String message() {
        return String.format("Height should be between %d and %d.", MIN_WEIGHT, MAX_WEIGHT);
    }

    @Override
    public void initialize(ValidWeight validWeight) {
        ConstraintValidator.super.initialize(validWeight);
    }

    @Override
    public boolean isValid(Integer weight,
                           ConstraintValidatorContext cxt) {
        return (weight > MIN_WEIGHT && weight < MAX_WEIGHT);
    }

}