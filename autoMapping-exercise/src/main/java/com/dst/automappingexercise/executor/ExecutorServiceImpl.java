package com.dst.automappingexercise.executor;

import com.dst.automappingexercise.util.exceptions.NoLoggedUserFoundException;
import com.dst.automappingexercise.games.Game;
import com.dst.automappingexercise.games.dtos.GameAddDto;
import com.dst.automappingexercise.games.GameService;
import com.dst.automappingexercise.games.dtos.GameDetailsDto;
import com.dst.automappingexercise.games.dtos.GamesDto;
import com.dst.automappingexercise.users.User;
import com.dst.automappingexercise.users.dtos.UserLoginDTO;
import com.dst.automappingexercise.users.dtos.UserRegisterDTO;
import com.dst.automappingexercise.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public ExecutorServiceImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public String execute(String commandLine) {
        String[] commandParams = commandLine.split("\\|");

        String command = commandParams[0];

        return switch (command) {
            case REGISTER_USER_COMMAND -> registerUser(commandParams);
            case LOGIN_USER_COMMAND -> loginUser(commandParams);
            case LOGOUT_USER_COMMAND -> logout();
            case ADD_GAME_COMMAND -> addGame(commandParams);
            case EDIT_GAME_COMMAND -> editGame(commandParams);
            case DELETE_GAME_COMMAND -> deleteGame(commandParams);
            case ALL_GAMES_COMMAND -> printAllGames();
            case GAME_DETAILS_COMMAND -> getGameDetails(commandParams);
            case END_COMMAND -> PROGRAM_END_MESSAGE;
            default -> UNKNOWN_COMMAND_MESSAGE;
        };
    }

    private String getGameDetails(String[] commandParams) {
        String title = commandParams[1];
        GameDetailsDto gameDetails = this.gameService.findByTitle(title);

        return String.format(GET_GAME_DETAILS_MESSAGE,
                gameDetails.getTitle(),
                gameDetails.getPrice(),
                gameDetails.getDescription(),
                gameDetails.getReleaseDate().format(DateTimeFormatter.ofPattern("d-M-yyyy")));
    }

    private String printAllGames() {
        List<GamesDto> gamesList = this.gameService.findAll();

        StringBuilder output = new StringBuilder();
        gamesList.forEach(game -> output.append(game.getTitle()).append(" ").append(game.getPrice()).append("\n"));

        return output.toString().trim();
    }

    private String deleteGame(String[] commandParams) {
        Game game = this.gameService.delete(commandParams);

        return String.format(DELETE_GAME_MESSAGE, game.getTitle());
    }

    private String editGame(String[] commandParams) {
        Game game = this.gameService.edit(commandParams);

        return String.format(EDIT_GAME_MESSAGE, game.getTitle());
    }

    private String addGame(String[] commandParams) {
        GameAddDto gameData = new GameAddDto(commandParams);
        Game game = this.gameService.add(gameData);

        return String.format(ADD_GAME_MESSAGE, game.getTitle());
    }

    private String logout() {
        User user = this.userService.getLoggedUser();

        try {
            this.userService.logout();
        } catch (NoLoggedUserFoundException e) {
            return e.getMessage();
        }


        return String.format(LOGOUT_USER_MESSAGE, user.getFullName());
    }

    private String loginUser(String[] commandParams) {
        UserLoginDTO loginData = new UserLoginDTO(commandParams);
        Optional<User> user = this.userService.login(loginData);

        if (user.isPresent()) {
            return String.format(LOGIN_USER_MESSAGE, user.get().getFullName(), user.get().getAdmin() ? "Admin" : "User");
        }

        return "Wrong credentials";
    }

    private String registerUser(String[] commandParams) {
        UserRegisterDTO registerData = new UserRegisterDTO(commandParams);
        User user = this.userService.register(registerData);

        return String.format(REGISTERED_USER_MESSAGE, user.getFullName());
    }
}
