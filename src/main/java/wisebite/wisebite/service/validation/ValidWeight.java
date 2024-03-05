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
    String message() default "Invalid weight.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
class WeightValidator implements
        ConstraintValidator<ValidWeight, Double> {
    private final Double MIN_WEIGHT = 40.0;
    private final Double MAX_WEIGHT = 300.0;

    @Override
    public void initialize(ValidWeight validWeight) {
        ConstraintValidator.super.initialize(validWeight);
    }

    @Override
    public boolean isValid(Double weight,
                           ConstraintValidatorContext context) {
        if (weight != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("Weight should be between %.2f and %.2f.", MIN_WEIGHT, MAX_WEIGHT))
                    .addConstraintViolation();
            return (weight > MIN_WEIGHT && weight < MAX_WEIGHT);
        } else {
            return false;
        }
    }

}