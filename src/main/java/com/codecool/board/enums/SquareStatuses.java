package com.codecool.board.enums;

public enum SquareStatuses {
    EMPTY('~'),
    SHIP('S'),
    HIT('H'),
    MISSED('M'),
    DEAD('D');

    private char statusName;

    SquareStatuses(char statusName) {
        this.statusName = statusName;
    }

    public char getCharacter() {
        return statusName;
    }


}
