package com.Rpg.validator.image.implement;

import com.Rpg.config.exception.image.ImageNotFoundException;
import com.Rpg.validator.image.ImageValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageValidatorImplement implements ImageValidator {

    @Override
    public void validate(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.getSize() <= 0) {
            throw new ImageNotFoundException("Image not found");
        }
    }
}
