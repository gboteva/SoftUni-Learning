package com.example.springautomapping.services;

import com.example.springautomapping.models.dto.GameAddDTO;
import com.example.springautomapping.models.dto.GameViewDetailsDTO;
import com.example.springautomapping.models.dto.GameViewTitleAndPriceDTO;
import com.example.springautomapping.models.entities.Game;
import com.example.springautomapping.models.entities.User;
import com.example.springautomapping.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;

    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGame(GameAddDTO gameDTO) {

        checkIfThereIsLoggedInUser();

        checkAccessOfLoggedUser();

        Game mappedGame = modelMapper.map(gameDTO, Game.class);
        gameRepository.save(mappedGame);
        System.out.println("Added "+ mappedGame.getTitle());

    }

    @Override
    public void editGame(Long id, List<String> tokens) {
        checkIfThereIsLoggedInUser();

        checkAccessOfLoggedUser();

        Game game = getGameFromDB(id);

        for(String token : tokens){
            String[] tokenArr = token.split("=");
            String field = tokenArr[0];
            String value = tokenArr[1];

            switch (field){
                case "title":
                    game.setTitle(value);
                    break;
                case "price":
                    game.setPrice(new BigDecimal(value));
                    break;
                case "size":
                    game.setSize(Double.parseDouble(value));
                    break;
                case "trailer":
                    game.setTrailer(value);
                    break;
                case "thumbnailURL":
                    game.setImageThumbnail(value);
                    break;
                case "description":
                    game.setText(value);
                    break;
                case "releaseDate":
                    game.setReleaseDate(LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    break;
            }
        }

        gameRepository.save(game);
        System.out.println("Edited " + game.getTitle());
    }


    @Override
    public void deleteGame(Long id) {

        checkIfThereIsLoggedInUser();
        checkAccessOfLoggedUser();

        Game game = getGameFromDB(id);

        gameRepository.delete(game);
        System.out.println("Deleted " + game.getTitle());
    }

    @Override
    public void printInfoForAllGames() {

        List<Game> allGames = gameRepository.findAll();

        List<GameViewTitleAndPriceDTO> viewDTOS = new ArrayList<>();

        allGames.forEach(game ->viewDTOS.add(modelMapper.map(game, GameViewTitleAndPriceDTO.class)));

        viewDTOS.forEach(System.out::println);

    }

    @Override
    public void printDetailInfoAboutGameWith(String title) {

        Game game = gameRepository.findByTitle(title);

        if (game == null ){
            throw new IllegalArgumentException("This game doesn't exist!");
        }

        System.out.println(modelMapper.map(game, GameViewDetailsDTO.class));
    }

    @Override
    public Game getGameFromDB(Object value) {
        Game game = null;
        if (value.getClass().getSimpleName().equals("Long")){
            game =  gameRepository.findById(Long.parseLong(value.toString())).orElse(null);
        }else if (value.getClass().getSimpleName().equals("String")){
            game = gameRepository.findByTitle(value.toString());
        }


        if (game == null){
            throw new IllegalArgumentException("The game doesn't exist!");
        }

        return game;
    }

//    @Override
//    public void buyGame(Long gameId) {
//        Game game = getGameFromDB(gameId);
//
//        checkIfThereIsLoggedInUser();
//
//        User loggedInUser = userService.getLoggedInUser();
//        loggedInUser.getGames().add(game);
//
//        userService.saveEntity(loggedInUser);
//
//        System.out.println("You successfully bye game " + game.getTitle());
//    }


    private void checkAccessOfLoggedUser() {
        User loggedInUser = userService.getLoggedInUser();
        if (!loggedInUser.isAdmin()){
            throw new IllegalStateException("You don't have necessary access!");
        }
    }

    private void checkIfThereIsLoggedInUser() {
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser == null){
            throw new IllegalStateException("There's no logged in user!");
        }
    }





}
