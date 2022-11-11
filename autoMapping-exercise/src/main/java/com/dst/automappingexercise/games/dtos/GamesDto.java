package com.dst.automappingexercise.games.dtos;

import java.math.BigDecimal;

public class GamesDto {
    private String title;
    private BigDecimal price;

    public GamesDto() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
