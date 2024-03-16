package org.example.cinemabackend.movie.core.domain;

import java.text.SimpleDateFormat;
import java.util.Set;

public class Movie extends AbstractEntity<Long>{
    private String title;
    private String originalTitle;
    private Double duration;
    private SimpleDateFormat releaseDate;
    private ProductionDetails productionDetails;
    private Description description;
    private SubtitleAndSoundOptions subtitleAndSoundOptions;
    private AgeRestriction ageRestriction;
    private VideoFile movieFile;
    private Set<Image> images;
    private Set<VideoFile> trailers;
    private Set<Genre> genres;
    private Set<ProjectionTechnology> projectionTechnologies;
}
