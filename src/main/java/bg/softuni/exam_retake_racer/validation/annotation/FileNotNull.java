package bg.softuni.exam_retake_racer.validation.annotation;

import bg.softuni.exam_retake_racer.validation.FileNotNullValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileNotNullValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotNull {

    String message() default "File cannot be null";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

}
