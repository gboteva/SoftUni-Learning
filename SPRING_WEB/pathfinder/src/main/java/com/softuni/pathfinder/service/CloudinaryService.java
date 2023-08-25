package com.softuni.pathfinder.service;

import com.softuni.pathfinder.model.cloudinary.CloudinaryImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImage uploadImage(MultipartFile multipartFile) throws IOException;

    boolean deleteImage(String publicKey);
}
