package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateImageRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateImageRequest;
import org.example.cinemabackend.cinema.application.dto.response.ImageResponse;
import org.example.cinemabackend.movie.core.domain.Image;

public interface ImageMapper {
    Image mapCreateImageRequestToImage(CreateImageRequest image);

    ImageResponse mapImageToImageResponse(Image image);

    Image mapUpdateImageRequestToImage(UpdateImageRequest image, Image moviePoster);
}
