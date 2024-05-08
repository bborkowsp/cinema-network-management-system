package org.example.cinemabackend.shared.seeder;

import org.example.cinemabackend.movie.core.domain.Image;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
class ImageUtil {

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
