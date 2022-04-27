package com.example.RickAndMorty.service;

import com.example.RickAndMorty.model.EpisodesAndSeasonsDTO;
import com.example.RickAndMorty.model.Result;
import com.example.RickAndMorty.model.RickAndMorty;
import com.example.RickAndMorty.model.SeasonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InfoService { //TODO divide the service class into specialized service classes - episodes, characters, seasons,...
    private final RestTemplate restTemplate = new RestTemplate();

    public int infoPages() {
        RickAndMorty rickAndMortyInfo = restTemplate
                .getForObject("https://rickandmortyapi.com/api/episode/", RickAndMorty.class);
        int pages = rickAndMortyInfo.getInfo().getPages();
        //FIXME getInfo() may throw NPE.
        //TODO return rickAndMortyInfo.getInfo().getPages(); -> pages is not needed

        return pages;
    }

    public int infoCount() {
        RickAndMorty rickAndMortyInfo = restTemplate
                .getForObject("https://rickandmortyapi.com/api/episode/", RickAndMorty.class);
        int count = rickAndMortyInfo.getInfo().getCount();
        //FIXME getInfo() may throw NPE.
        //TODO return rickAndMortyInfo.getInfo().getCount(); -> count is not needed
        return count;
    }

    public List<Result> getListOfAllEpisodes() {
        //FIXME get all episodes can be taken from https://rickandmortyapi.com/api/episode and no loops necessary
        List<Result> rickyAndMortyEpisodesList = new ArrayList<>();
        for (int id = 1; id <= infoPages(); id++) {
            RickAndMorty episodesListFromPage = restTemplate
                    .getForObject("https://rickandmortyapi.com/api/episode?page=" + id, RickAndMorty.class);
            List<Result> pageList = new ArrayList<>(episodesListFromPage.getResults());
            //FIXME getResults() may throw NPE.
            rickyAndMortyEpisodesList.addAll(pageList);
        }
        return rickyAndMortyEpisodesList;
    }

    public List<Integer> getListOfseasonNumber(List<Result> rickyAndMortyEpisodesList) {
        //FIXME the parameter is unused.
        List<Integer> seasons = new ArrayList<>();
        Iterator<Result> episodeIterator = getListOfAllEpisodes().iterator();

        while (episodeIterator.hasNext()) {
            //TODO nice use of Iterator, howevew unnecessary - forach would be enough
            String episodeNumber = episodeIterator.next().getEpisode();
            int season = Character.getNumericValue(episodeNumber.toCharArray()[2]); //TODO magic number
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
                    .id(entry.getKey())
                    .episodes(entry.getValue())
                    .build();
            listOfSeasons.add(seasonDTO);
        }
        return listOfSeasons;
    }

    public List<EpisodesAndSeasonsDTO> getSeasonNumberAndEpisodesId(List<Result> rickyAndMortyEpisodesList) {
        List<EpisodesAndSeasonsDTO> seasonsAndEpisodesList = new ArrayList<>();
        //TODO zamienić na foreach
        for (int i = 0; i < rickyAndMortyEpisodesList.size(); i++) {
            String episodeNumber = rickyAndMortyEpisodesList.get(i).getEpisode();
            int season = Character.getNumericValue(episodeNumber.toCharArray()[2]); // TODO int seasonId lub seasonNumber
            int episode = rickyAndMortyEpisodesList.get(i).getId(); //TODO int episodeId
            EpisodesAndSeasonsDTO episodesAndSeasonsDTO = EpisodesAndSeasonsDTO.builder()
                    .episodeId(episode)
                    .seasonNumber(season)
                    .build();
            seasonsAndEpisodesList.add(episodesAndSeasonsDTO);
        }
        return seasonsAndEpisodesList;
    }
}
