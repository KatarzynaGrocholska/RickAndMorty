package com.example.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class EpisodesAndSeasonsDTO {
    private Integer seasonNumber;
    private Integer episodeId;
}
