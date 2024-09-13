package org.example.cinemabackend._shared.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileService {

    public String saveImageToFileSystem(MultipartFile image, String uploadDirectory) {
        final String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDirectory + fileName);
        try {
            Files.write(filePath, image.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to save image " + fileName);
        }
        return fileName;
    }

    public void deleteOldImageFromFileSystem(String image, String uploadDirectory) {
        try {
            Files.deleteIfExists(Paths.get(uploadDirectory + image));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to delete image " + image);
        }
    }
}
