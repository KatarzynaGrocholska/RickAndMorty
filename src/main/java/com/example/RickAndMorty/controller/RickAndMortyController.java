package com.example.RickAndMorty.controller;


import com.example.RickAndMorty.model.EpisodesAndSeasonsDTO;
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

    @GetMapping(path = "/episode/{id}")//odcinek wyciągany po id
    public EpisodesDTO getResultById(@PathVariable("id") Integer id) {
        Integer idNumber = id <= infoService.infoCount() ? id : infoService.infoCount();
        return resultsService.getEpisodeById(idNumber);
    }

    @GetMapping(path = "/seasons_list") // lista sezonów i liczba odcinków w sezonie
    public List<SeasonDTO> allSeasonsListWithEpisodesNumber() {
        return infoService.changeMapToSeasonDTO
                (infoService.countByStreamToMapEpisodes
                        (infoService.getListOfseasonNumber
                                (infoService.getListOfAllEpisodes())));
    }

    @GetMapping(path = "/seasons_episodes")// id odcinka i numer sezonu
    public List<EpisodesAndSeasonsDTO> allSeasonsListWithEpisodesId() {
        return infoService
                .getSeasonNumberAndEpisodesId(infoService.getListOfAllEpisodes());
    }
}
