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
    //TODO can the seasonNumber of episodeId be <0?
    private Integer seasonNumber; //TODO why is this an object?
    private Integer episodeId; //TODO why is this an object?
}
