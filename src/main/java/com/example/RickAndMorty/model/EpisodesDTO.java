package com.example.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class EpisodesDTO {

    private String name;
    private String air_date; //TODO why is this a String, if it is a date?
    private List<String> characters;
}
