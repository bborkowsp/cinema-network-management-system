package org.example.cinemabackend.shared.seeder;

import org.example.cinemabackend.movie.core.domain.Image;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

@Component
class ImageSeeder {

    Set<Image> createImages() {
        Set<Image> images = new HashSet<>();
        images.add(createImage());
        return images;
    }

    Image createImage() {
        try {
            File image = new File("src/main/java/org/example/cinemabackend/shared/seeder/images/poster.jpg");
            String name = image.getName();
            String type = Files.probeContentType(image.toPath());
            byte[] data = Files.readAllBytes(image.toPath());
            return new Image(name, type, data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
