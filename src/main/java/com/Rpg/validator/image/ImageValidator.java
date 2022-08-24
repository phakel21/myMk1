package com.Rpg.validator.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageValidator {

    void validate(MultipartFile multipartFile);
}
