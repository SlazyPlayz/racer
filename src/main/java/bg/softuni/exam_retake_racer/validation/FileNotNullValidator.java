package bg.softuni.exam_retake_racer.validation;

import bg.softuni.exam_retake_racer.validation.annotation.FileNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileNotNullValidator implements ConstraintValidator<FileNotNull, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value.getSize() > 0;
    }
}
