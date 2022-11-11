package com.dst.automappingexercise.games;

import com.dst.automappingexercise.util.exceptions.AccessDeniedException;
import com.dst.automappingexercise.util.exceptions.GameNotFoundException;
import com.dst.automappingexercise.games.dtos.GameAddDto;
import com.dst.automappingexercise.games.dtos.GameDetailsDto;
import com.dst.automappingexercise.games.dtos.GamesDto;
import com.dst.automappingexercise.users.UserServiceImpl;
import com.dst.automappingexercise.util.validators.GameDataValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game add(GameAddDto gameData) {

        if (!UserServiceImpl.isAdmin) {
            throw new AccessDeniedException("Don't have permissions to add/edit/remove games.");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        PropertyMap<GameAddDto, Game> employeeMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setTrailerId(source.getTrailerId());
            }
        };
        Game game = new Game();
        modelMapper.addMappings(employeeMap).map(gameData, game);

        return this.gameRepository.save(game);
    }

    @Override
    public Game edit(String[] gameData) {

        if (!UserServiceImpl.isAdmin) {
            throw new AccessDeniedException("Don't have permissions to add/edit/remove games.");
        }

        long id = Long.parseLong(gameData[1]);

        Optional<Game> game = this.gameRepository.findById(id);

        if (game.isEmpty()) {
            throw new GameNotFoundException(String.format("Game with id %d not found", id));
        }

        Arrays.stream(gameData).skip(2).forEach(param -> {
            String property = param.split("=")[0];
            String value = param.split("=")[1];

            switch (property) {
                case "title" -> {
                    game.get().setTitle(value);
                }
                case "price" -> game.get().setPrice(new BigDecimal(value));
                case "size" -> game.get().setSize(Double.parseDouble(value));
                case "trailerId" -> game.get().setTrailerId(value);
                case "thumbnailUrl" -> game.get().setThumbnailUrl(value);
                case "description" -> game.get().setDescription(value);
                case "releaseDate" ->
                        game.get().setReleaseDate(LocalDate.parse(value, DateTimeFormatter.ofPattern("d-M-yyyy")));
            }
        });
        GameDataValidator.validateAddGameInformation(game.get().getTitle(), game.get().getPrice(), game.get().getSize(),
                game.get().getTrailerId(), game.get().getThumbnailUrl(), game.get().getDescription());

        return this.gameRepository.save(game.get());
    }

    @Override
    public Game delete(String[] gameData) {

        if (!UserServiceImpl.isAdmin) {
            throw new AccessDeniedException("Don't have permissions to add/edit/remove games.");
        }

        long id = Long.parseLong(gameData[1]);

        Optional<Game> game = this.gameRepository.findById(id);

        if (game.isEmpty()) {
            throw new GameNotFoundException(String.format("Game with id %d not found", id));
        }

        this.gameRepository.delete(game.get());

        return game.get();
    }

    @Override
    public List<GamesDto> findAll() {
        ModelMapper mapper = new ModelMapper();

        List<Game> games = this.gameRepository.findAll();

        return games.stream().map(game -> mapper.map(game, GamesDto.class)).collect(Collectors.toList());
    }

    @Override
    public GameDetailsDto findByTitle(String title) {
        Optional<Game> game = this.gameRepository.findFirstByTitle(title);

        if (game.isEmpty()) {
            throw new GameNotFoundException(String.format("Game %s was not found in the database", title));
        }

        ModelMapper mapper = new ModelMapper();

        return mapper.map(game.get(), GameDetailsDto.class);
    }
}
