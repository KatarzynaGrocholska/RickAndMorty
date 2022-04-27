package com.example.RickAndMorty.service;


import com.example.RickAndMorty.model.EpisodesDTO;
import com.example.RickAndMorty.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class ResultsService {

    private final RestTemplate restTemplate = new RestTemplate();

    public EpisodesDTO getEpisodeById(Integer id) { //TODO int instead of Integer
        if (id != null) { // TODO if it was int, this would be unnecessary
            Result rickAndMortyEpisode = restTemplate
                    .getForObject("https://rickandmortyapi.com/api/episode/" + id, Result.class);
            //FIXME rickAndMortyEpisode could be null, gotta check for that
            //TODO unnecessary variable -> return EpisodesDTO.builder().....
            EpisodesDTO episodesDTO = EpisodesDTO.builder()
                    .air_date(rickAndMortyEpisode.getAir_date())
                    .characters(rickAndMortyEpisode.getCharacters())
                    .name(rickAndMortyEpisode.getName())
                    .build();

            return episodesDTO;
        }
        return null;
    }
}



