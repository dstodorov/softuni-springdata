package com.dst.automappingexercise.util.validators;

import com.dst.automappingexercise.util.exceptions.GameValidationException;

import java.math.BigDecimal;

import static com.dst.automappingexercise.util.validators.ValidationMessages.*;

public class GameDataValidator {
    private static final String THUMBNAIL_HTTP = "http://";
    private static final String THUMBNAIL_HTTPS = "https://";


    public static void validateAddGameInformation(String title, BigDecimal price, double size, String trailerId, String thumbnailUrl, String description) {
        validateTitle(title);
        validatePrice(price);
        validateSize(size);
        validateTrailer(trailerId);
        validateThumbnail(thumbnailUrl);
        validateDescription(description);
    }

    private static void validateTitle(String title) {
        boolean isUppercase = Character.isUpperCase(title.charAt(0));
        boolean isLetter = Character.isLetter(title.charAt(0));

        if (title.length() < 3 || title.length() > 100 || !isUppercase || !isLetter) {
            throw new GameValidationException(WRONG_GAME_TITLE_MESSAGE);
        }
    }

    private static void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ONE) < 0) {
            throw new GameValidationException(WRONG_GAME_PRICE_MESSAGE);
        }
    }

    private static void validateSize(double size) {
        if (size < 1) {
            throw new GameValidationException(WRONG_GAME_SIZE_MESSAGE);
        }
    }

    private static void validateTrailer(String trailerId) {
        if (trailerId.length() != 11) {
            throw new GameValidationException(WRONG_GAME_TRAILER_MESSAGE);
        }
    }

    private static void validateThumbnail(String thumbnail) {
        if (!thumbnail.startsWith(THUMBNAIL_HTTP) && !thumbnail.startsWith(THUMBNAIL_HTTPS)) {
            throw new GameValidationException(WRONG_GAME_THUMBNAIL_MESSAGE);
        }
    }

    private static void validateDescription(String description) {
        if (description.length() < 20) {
            throw new GameValidationException(WRONG_GAME_DESCRIPTION_MESSAGE);
        }
    }
}
