package com.codecool.board;

import com.codecool.board.enums.SquareStatuses;

public class Square {
    private Integer X;
    private Integer Y;
    private SquareStatuses squareStatuses;


    public Square(Integer x, Integer y, SquareStatuses squareStatuses) {
        X = x;
        Y = y;
        this.squareStatuses = squareStatuses;

    }

    public Integer getX() {
        return X;
    }

    public void setX(Integer x) {
        X = x;
    }

    public Integer getY() {
        return Y;
    }

    public void setY(Integer y) {
        Y = y;
    }

    public SquareStatuses getSquareStatuses() {
        return squareStatuses;
    }

    public void setSquareStatuses(SquareStatuses squareStatuses) {
        this.squareStatuses = squareStatuses;
    }
}
