package com.codecool.board;

import com.codecool.board.enums.SquareStatuses;



public class Board {

    private final int boardSize = 10;
    private Square[][] ocean = new Square[boardSize][boardSize];


    public void initBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++){
                ocean[i][j] = new Square(i, j, SquareStatuses.EMPTY);
            }
        }
    }

    public Board() {
        initBoard();
    }

    public Square[][] getBoard() {
        return ocean;
    }


}





