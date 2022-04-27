package com.example.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info { // TODO info about what?
    private Integer count; // TODO why is this an object and not int?
    private Integer pages;// TODO why is this an object and not int?
    private String next; //TODO what is this? an address? a url?
    private String prev; //TODO what is this? an address? a url?

    @Override
    public String toString() {
        return "Info{" +
                "count=" + count +
                ", pages=" + pages +
                ", next='" + next + '\'' +
                ", prev='" + prev + '\'' +
                '}';
    }
}
