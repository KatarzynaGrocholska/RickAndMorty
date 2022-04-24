package com.example.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;

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
