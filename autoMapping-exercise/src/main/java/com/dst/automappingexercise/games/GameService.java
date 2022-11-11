package com.dst.automappingexercise.games;

import com.dst.automappingexercise.games.dtos.GameAddDto;
import com.dst.automappingexercise.games.dtos.GameDetailsDto;
import com.dst.automappingexercise.games.dtos.GamesDto;

import java.util.List;

public interface GameService {
    Game add(GameAddDto gameData);

    Game edit(String[] gameData);

    Game delete(String[] gameData);

    List<GamesDto> findAll();

    GameDetailsDto findByTitle(String title);
}
