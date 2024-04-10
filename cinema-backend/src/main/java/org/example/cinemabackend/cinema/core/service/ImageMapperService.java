package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.request.CreateImageRequest;
import org.example.cinemabackend.cinema.application.dto.response.ImageResponse;
import org.example.cinemabackend.cinema.core.port.primary.ImageMapper;
import org.example.cinemabackend.movie.core.domain.Image;
import org.springframework.stereotype.Service;

@Service
class ImageMapperService implements ImageMapper {

    @Override
    public Image mapCreateImageRequestToImage(CreateImageRequest image) {
        return null;
    }

    @Override
    public ImageResponse mapImageToImageResponse(Image image) {
        return ImageResponse.builder()
                .data(image.getData())
                .build();
    }
}
