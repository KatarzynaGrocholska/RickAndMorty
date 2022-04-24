package com.example.RickAndMorty.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SeasonDTO {

    private Integer id;
    private Long episodes;
}
