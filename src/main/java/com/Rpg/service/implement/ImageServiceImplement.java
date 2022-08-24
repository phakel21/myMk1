package com.Rpg.service.implement;

import com.Rpg.service.ImageService;
import com.Rpg.validator.image.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImplement implements ImageService {

    @Value("${file.path}")
    private String filePath;

    private List<ImageValidator> imageValidators;

    @Autowired
    public ImageServiceImplement(List<ImageValidator> imageValidators) {
        this.imageValidators = imageValidators;
    }

    @Override
    public String saveFile(String path, MultipartFile multipartFile) throws IOException {
        for (ImageValidator imageValidator : imageValidators) {
            imageValidator.validate(multipartFile);
        }

        File dir = new File(filePath + path);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String uuid = UUID.randomUUID().toString();
        String resultFileName = uuid + "." + multipartFile.getOriginalFilename();
        String forImage = filePath + path + "/" + resultFileName;
        File file = new File(forImage);
        multipartFile.transferTo(file);
        System.out.println(multipartFile.getSize());
        return resultFileName;
    }


    @Override
    public void deleteFile(String path, String fileName) {
        File file = new File(filePath + path + "/", fileName);
        if (file.exists())
            file.delete();

    }

}
