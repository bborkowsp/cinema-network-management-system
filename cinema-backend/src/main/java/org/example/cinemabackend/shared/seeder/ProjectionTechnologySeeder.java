package org.example.cinemabackend.shared.seeder;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(0)
class ProjectionTechnologySeeder implements Seeder {

    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private final Faker faker;
    private int increment = 0;

    @Override
    public void seedDatabase(int objectsToSeed) {
        Set<ProjectionTechnology> projectionTechnologies = new HashSet<>();
        while (projectionTechnologies.size() < objectsToSeed) {
            final var projectionTechnology = createProjectionTechnology();
            projectionTechnologyRepository.save(projectionTechnology);
            projectionTechnologies.add(projectionTechnology);
            increment++;
        }
    }

    private ProjectionTechnology createProjectionTechnology() {
        increment++;
        return new ProjectionTechnology(faker.company().buzzword() + increment, faker.lorem().sentence(8));
    }
}
