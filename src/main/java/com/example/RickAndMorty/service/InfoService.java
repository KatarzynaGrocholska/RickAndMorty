package com.example.RickAndMorty.service;

import com.example.RickAndMorty.model.Result;
import com.example.RickAndMorty.model.RickAndMorty;
import com.example.RickAndMorty.model.SeasonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InfoService {
    private final RestTemplate restTemplate = new RestTemplate();

    public int infoPages() {
        RickAndMorty rickAndMortyInfo = restTemplate
                .getForObject("https://rickandmortyapi.com/api/episode/", RickAndMorty.class);
        int pages = rickAndMortyInfo.getInfo().getPages();

        return pages;
    }

    public List<Result> getListOfAllEpisodes() {
        List<Result> rickyAndMortyEpisodesList = new ArrayList<>();
        for (int id = 1; id <= infoPages(); id++) {
            RickAndMorty episodesListFromPage = restTemplate
                    .getForObject("https://rickandmortyapi.com/api/episode?page=" + id, RickAndMorty.class);
            List<Result> pageList = new ArrayList<>(episodesListFromPage.getResults());
            rickyAndMortyEpisodesList.addAll(pageList);
        }
        return rickyAndMortyEpisodesList;
    }

    public List<Integer> getListOfseasonNumber(List<Result> rickyAndMortyEpisodesList) {
        List<Integer> seasons = new ArrayList<>();
        Iterator<Result> episodeIterator = getListOfAllEpisodes().iterator();

        while (episodeIterator.hasNext()) {
            String episodeNumber = episodeIterator.next().getEpisode();
            int season = (int) episodeNumber.toCharArray()[2];
            seasons.add(season);
        }
        return seasons;
    }

    public Map<Integer, Long> countByStreamToMapEpisodes(List<Integer> seasons) {
        Map<Integer, Long> seasonsMap = seasons.stream()
                .collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum));
        return seasonsMap;
    }

    public List<SeasonDTO> changeMapToSeasonDTO(Map<Integer, Long> seasonMap) {
        List<SeasonDTO> listOfSeasons = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : seasonMap.entrySet()) {
            SeasonDTO seasonDTO = SeasonDTO.builder()
                    .id(entry.getKey() - 48)
                    .episodes(entry.getValue())
                    .build();
            listOfSeasons.add(seasonDTO);
        }
        return listOfSeasons;
    }
}
