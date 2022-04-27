package com.example.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
public class Result { //TODO result of what?
    private Integer id; //TODO why not int?
    private String name; //TODO can it be empty?
    private String air_date; //TODO maybe date?
    private String episode; //TODO maybe class?
    private List<String> characters; //TODO maybe List<Character>?
    private String url;
    private String created; //TODO is it 'createdBy'? or 'isCreated'?


}
