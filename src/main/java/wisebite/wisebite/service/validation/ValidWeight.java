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
        ConstraintValidator<ValidWeight, Double> {
    private final Double MIN_WEIGHT = 40.0;
    private final Double MAX_WEIGHT = 300.0;
    private String message() {
        return String.format("Height should be between %f and %f.", MIN_WEIGHT, MAX_WEIGHT);
    }

    @Override
    public void initialize(ValidWeight validWeight) {
        ConstraintValidator.super.initialize(validWeight);
    }

    @Override
    public boolean isValid(Double weight,
                           ConstraintValidatorContext cxt) {
        if (weight != null) {
            return (weight > MIN_WEIGHT && weight < MAX_WEIGHT);
        } else {
            return false;
        }
    }

}