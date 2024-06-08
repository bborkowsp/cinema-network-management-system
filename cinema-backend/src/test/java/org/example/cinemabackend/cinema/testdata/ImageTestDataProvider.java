package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateImageRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateImageRequest;

public class ImageTestDataProvider {

    public static CreateImageRequest generateCreateImageRequest() {
        return CreateImageRequest.builder()
                .name("Name")
                .type("jpeg/png")
                .data(new byte[]{1, 0, 1, 0})
                .build();
    }

    public static UpdateImageRequest generateUpdateImageRequest() {
        return UpdateImageRequest.builder()
                .name("Update name")
                .type("jpeg/png")
                .data(new byte[]{1, 0, 1, 0})
                .build();
    }

}
