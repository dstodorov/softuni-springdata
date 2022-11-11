package com.dst.automappingexercise.games.dtos;

import com.dst.automappingexercise.util.validators.GameDataValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GameAddDto {
    private final String title;
    private final BigDecimal price;
    private final double size;
    private final String trailerId;
    private final String thumbnailUrl;
    private final String description;
    private final LocalDate releaseDate;

    public GameAddDto(String[] commandParams) {
        this.title = commandParams[1];
        this.price = new BigDecimal(commandParams[2]);
        this.size = Double.parseDouble(commandParams[3]);
        this.trailerId = commandParams[4];
        this.thumbnailUrl = commandParams[5];
        this.description = commandParams[6];
        this.releaseDate = LocalDate.parse(commandParams[7], DateTimeFormatter.ofPattern("d-M-yyyy"));

        GameDataValidator.validateAddGameInformation(this.title, this.price, this.size, this.trailerId, this.thumbnailUrl, this.description);
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getSize() {
        return size;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
