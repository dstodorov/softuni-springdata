package com.dst.automappingexercise.executor;

public interface ExecutorService {

    /*
    *
    * MENU INPUT MESSAGES
    *
    * */
    String REGISTER_USER_COMMAND = "RegisterUser";
    String LOGIN_USER_COMMAND = "LoginUser";
    String LOGOUT_USER_COMMAND = "Logout";
    String ADD_GAME_COMMAND = "AddGame";
    String EDIT_GAME_COMMAND = "EditGame";
    String DELETE_GAME_COMMAND = "DeleteGame";
    String ALL_GAMES_COMMAND = "AllGames";
    String GAME_DETAILS_COMMAND = "DetailGame";
    String END_COMMAND = "END";


    /*
    *
    *
    * OPERATIONS RETURN MESSAGES
    *
    * */

    String REGISTERED_USER_MESSAGE = "%s is registered";
    String LOGIN_USER_MESSAGE = "%s successfully logged in as [%s]";
    String LOGOUT_USER_MESSAGE = "User %s is successfully logged out";
    String ADD_GAME_MESSAGE = "Added %s";
    String EDIT_GAME_MESSAGE = "Edited %s";
    String DELETE_GAME_MESSAGE = "Deleted %s";
    String GET_GAME_DETAILS_MESSAGE = "Title: %s%nPrice: %.2f%nDescription: %s%nReleaseDate: %s";
    String UNKNOWN_COMMAND_MESSAGE = "Unknown command";
    String PROGRAM_END_MESSAGE = "END";

    String execute(String command);
}
