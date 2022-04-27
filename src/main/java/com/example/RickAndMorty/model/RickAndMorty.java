package com.example.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RickAndMorty { //TODO class name is ambiguous - what does the class represent?
    private Info info;
    private List<Result> results;

}
