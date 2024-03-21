package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateImageRequest;
import org.example.cinemabackend.movie.core.domain.Image;

public interface ImageMapper {
    Image mapCreateImageRequestToImage(CreateImageRequest image);
}
