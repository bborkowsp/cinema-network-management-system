package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.response.ProductionDetailsResponse;
import org.example.cinemabackend.movie.core.domain.ProductionDetails;

public interface ProductionDetailsMapper {
    ProductionDetailsResponse mapProductionDetailsToProductionDetailsResponse(ProductionDetails productionDetails);
}