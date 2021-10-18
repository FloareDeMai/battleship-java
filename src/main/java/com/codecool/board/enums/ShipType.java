package com.codecool.board.enums;

import com.codecool.board.Board;

public enum ShipType {
    CARRIER("\033[0;31m" + "Carrier" + "\u001B[0m", 5),
    CRUISER("\u001B[32m" + "Cruiser" + "\u001B[0m", 4),
    BATTLESHIP("\u001B[34m"+"Battleship" + "\u001B[0m", 3),
    SUBMARINE("\033[0;93m" + "Submarine" + "\u001B[0m", 2),
    DESTROYER("\u001B[35m" + "Destroyer" + "\u001B[0m", 1);

    private String name;
    private Integer lengthOfShip;

    ShipType(String name, Integer lengthOfShip) {
        this.name = name;
        this.lengthOfShip = lengthOfShip;
    }

    public String getName() {
        return name;
    }

    public Integer getLengthOfShip() {
        return lengthOfShip;
    }
}
