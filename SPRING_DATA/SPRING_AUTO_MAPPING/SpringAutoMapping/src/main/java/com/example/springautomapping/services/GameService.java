package com.example.springautomapping.services;

import com.example.springautomapping.models.dto.GameAddDTO;
import com.example.springautomapping.models.entities.Game;

import java.util.List;
import java.util.Objects;

public interface GameService {
    void addGame(GameAddDTO gameDTO);

    void editGame(Long id, List<String> values);

    void deleteGame(Long id);

    void printInfoForAllGames();

    void printDetailInfoAboutGameWith(String title);

    Game getGameFromDB(Object value);

//    void buyGame(Long gameId);
}
