package com.Rpg.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String saveFile(String path, MultipartFile multipartFile) throws IOException;

    void deleteFile(String path, String fileName);
}
