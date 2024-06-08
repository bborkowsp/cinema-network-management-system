package org.example.cinemabackend.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.cinemabackend.cinema.testdata.ProjectionTechnologyTestDataProvider.generateCreateProjectionTechnologyRequest;
import static org.example.cinemabackend.cinema.testdata.ProjectionTechnologyTestDataProvider.generateProjectionTechnologies;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WithMockUser
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectionTechnologyControllerTest {

    private static final String PROJECTION_TECHNOLOGY_ENDPOINT_PATH = "/v1/projection-technologies";

    @Autowired
    private ProjectionTechnologyRepository projectionTechnologyRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void givenNoProjectionTechnologiesInDatabase_whenGetProjectionTechnologies_thenReturnEmptyList() throws Exception {
        //When, Then
        mockMvc.perform(get(PROJECTION_TECHNOLOGY_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isEmpty(),
                        jsonPath("$.content").isArray()
                );
    }

    @Test
    @Order(2)
    void givenProjectionTechnologiesInDatabase_whenGetProjectionTechnologies_thenReturnProjectionTechnologiesPage() throws Exception {
        //Given
        final var projectionTechnologies = generateProjectionTechnologies();
        projectionTechnologies.forEach(projectionTechnologyRepository::save);

        //When, Then
        mockMvc.perform(get(PROJECTION_TECHNOLOGY_ENDPOINT_PATH))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content[0].technology").value(projectionTechnologies.get(0).getTechnology()),
                        jsonPath("$.content[1].technology").value(projectionTechnologies.get(1).getTechnology()),
                        jsonPath("$.content[2].technology").value(projectionTechnologies.get(2).getTechnology())
                );
    }

    @Test
    @Order(3)
    void givenProjectionTechnologiesInDatabase_whenGetProjectionTechnologiesWithPageNumberAndSize_thenReturnProjectionTechnologiesPage() throws Exception {
        //Given
        final var projectionTechnologies = generateProjectionTechnologies();
        projectionTechnologies.forEach(projectionTechnologyRepository::save);

        final var pageNumber = "1";
        final var pageSize = "3";

        //When, Then
        mockMvc.perform(get(PROJECTION_TECHNOLOGY_ENDPOINT_PATH)
                        .param("page", pageNumber)
                        .param("size", pageSize))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(pageSize),
                        jsonPath("$.content[0].technology").value(projectionTechnologies.get(0).getTechnology()),
                        jsonPath("$.content[1].technology").value(projectionTechnologies.get(1).getTechnology()),
                        jsonPath("$.content[2].technology").value(projectionTechnologies.get(2).getTechnology())
                );
    }

    @Test
    @Order(4)
    void givenProjectionTechnologiesInDatabase_whenGetProjectionTechnology_thenReturnProjectionTechnology() throws Exception {
        //Given
        final var technology = projectionTechnologyRepository.findAll().getFirst();

        //When, Then
        mockMvc.perform(get(PROJECTION_TECHNOLOGY_ENDPOINT_PATH + "/" + technology.getTechnology()))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.technology").value(technology.getTechnology()),
                        jsonPath("$.description").value(technology.getDescription())
                );
    }

    @Test
    @Order(5)
    void givenProjectionTechnologiesInDatabase_whenCreateProjectionTechnology_thenReturnCreatedProjectionTechnology() throws Exception {
        //Given
        final var createProjectionTechnologyRequest = generateCreateProjectionTechnologyRequest();

        //When
        mockMvc.perform(post(PROJECTION_TECHNOLOGY_ENDPOINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProjectionTechnologyRequest)))
                .andExpectAll(
                        status().isCreated()
                );

        //Then
        assertThat(projectionTechnologyRepository.findByTechnology(createProjectionTechnologyRequest.technology())).isPresent();
    }

    @Test
    @Order(6)
    void givenAlreadyExistingProjectionTechnology_whenCreateProjectionTechnology_thenReturnBadRequest() throws Exception {
        //Given
        final var projectionTechnology = projectionTechnologyRepository.findAll().getFirst();
        final var createProjectionTechnologyRequest = new CreateProjectionTechnologyRequest(
                projectionTechnology.getTechnology(), "Description");

        //When, Then
        mockMvc.perform(post(PROJECTION_TECHNOLOGY_ENDPOINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProjectionTechnologyRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    void givenProjectionTechnologiesInDatabase_whenUpdateProjectionTechnology_thenReturnNoContent() throws Exception {
        //Given
        final var technology = projectionTechnologyRepository.findAll().getFirst();
        final var updateProjectionTechnologyRequest = new CreateProjectionTechnologyRequest(technology.getTechnology(), "New description");

        //When
        mockMvc.perform(patch(PROJECTION_TECHNOLOGY_ENDPOINT_PATH + "/" + technology.getTechnology())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateProjectionTechnologyRequest)))
                .andExpect(status().isNoContent());

        //Then
        final var updatedProjectionTechnology = projectionTechnologyRepository.findByTechnology(technology.getTechnology());
        assertThat(updatedProjectionTechnology).isPresent();
        assertThat(updatedProjectionTechnology.get().getDescription()).isEqualTo(updateProjectionTechnologyRequest.description());
    }

    @Test
    @Order(8)
    void givenProjectionTechnologiesInDatabase_whenDeleteProjectionTechnology_thenReturnNoContent() throws Exception {
        //Given
        final var technology = projectionTechnologyRepository.findAll().getFirst();

        //When
        mockMvc.perform(delete(PROJECTION_TECHNOLOGY_ENDPOINT_PATH + "/" + technology.getTechnology()))
                .andExpect(status().isOk());

        //Then
        mockMvc.perform(get(PROJECTION_TECHNOLOGY_ENDPOINT_PATH + "/" + technology.getTechnology()))
                .andExpect(status().isBadRequest());
    }


}
