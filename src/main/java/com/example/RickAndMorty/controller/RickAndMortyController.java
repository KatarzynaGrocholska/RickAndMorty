package com.example.RickAndMorty.controller;


import com.example.RickAndMorty.model.EpisodesDTO;
import com.example.RickAndMorty.model.Result;
import com.example.RickAndMorty.model.SeasonDTO;
import com.example.RickAndMorty.service.InfoService;
import com.example.RickAndMorty.service.ResultsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
@Slf4j
public class RickAndMortyController {

    private final ResultsService resultsService;
    private final InfoService infoService;

    @GetMapping(path = "/episode/{id}")
    @ResponseBody
    public EpisodesDTO getResultById(@PathVariable("id") Integer id) {
        Integer idNumber = id >= 1 ? id : 1;
        return resultsService.getEpisodeById(idNumber);
    }

    @GetMapping(path = "/seasons_list")
    public List<SeasonDTO> allSeasonsListwithEpisodes() {
        return infoService.changeMapToSeasonDTO(allSeasons());
    }

    @GetMapping(path = "/seasons_number")
    public List<Integer> allSeasonsList() {
        return infoService.getListOfseasonNumber(allEpisodes());
    }

    @GetMapping(path = "/seasons")
    public Map<Integer, Long> allSeasons() {
        return infoService.countByStreamToMapEpisodes(allSeasonsList());
    }

    @GetMapping(path = "/info")
    public int info() {
        return infoService.infoPages();
    }

    @GetMapping(path = "/all")
    public List<Result> allEpisodes() {
        return infoService.getListOfAllEpisodes();
    }
}
