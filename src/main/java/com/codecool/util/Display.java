package com.codecool.util;

import com.codecool.board.Square;
import com.codecool.board.enums.SquareStatuses;

public class Display {

    public void displayBoard(Square[][] ocean) {
        int rowNumber = 0;
        System.out.print("    ");
        for(int i = 0; i < ocean.length; ++i)
            System.out.print(++rowNumber + "   ");
        System.out.println();
        for(int i = 0; i< ocean.length; i++){
            System.out.print(" " + (char)(i + 'A') + "  ");

            for(int j = 0; j < ocean[0].length; j++){
                if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.EMPTY.getCharacter())
                    System.out.print("\u001B[36m" + SquareStatuses.EMPTY.getCharacter() +"\u001B[0m" + "   ");
                else if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.SHIP.getCharacter())
                    System.out.print("\033[1;97m" + SquareStatuses.SHIP.getCharacter()+ "\u001B[0m" + "   " );
                else if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.HIT.getCharacter())
                    System.out.print("\033[1;91m" + SquareStatuses.HIT.getCharacter() + "\u001B[0m" + "   ");
                else if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.MISSED.getCharacter())
                    System.out.print("\033[1;94m" + SquareStatuses.MISSED.getCharacter()+ "\u001B[0m" + "   ");
            }
            System.out.println();
        }
    }

    public void displayBoardForShooting(Square[][] ocean) {
        int rowNumber = 0;
        System.out.print("    ");
        for (int i = 0; i < ocean.length; ++i)
            System.out.print(++rowNumber + "   ");
        System.out.println();
        for (int i = 0; i < ocean.length; i++) {
            System.out.print(" " + (char) (i + 'A') + "  ");
            for(int j = 0; j < ocean[0].length; j++){
                if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.EMPTY.getCharacter())
                    System.out.print("\u001B[36m" + SquareStatuses.EMPTY.getCharacter() +"\u001B[0m" + "   ");
                else if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.SHIP.getCharacter())
                    System.out.print("\u001B[36m" + SquareStatuses.EMPTY.getCharacter() +"\u001B[0m" + "   ");
                else if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.HIT.getCharacter())
                    System.out.print("\033[1;91m" + SquareStatuses.HIT.getCharacter() + "\u001B[0m" + "   ");
                else if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.MISSED.getCharacter())
                    System.out.print("\033[1;94m" + SquareStatuses.MISSED.getCharacter()+ "\u001B[0m" + "   ");
                else if (ocean[i][j].getSquareStatuses().getCharacter() == SquareStatuses.DEAD.getCharacter())
                    System.out.print("\u001B[33m" + SquareStatuses.DEAD.getCharacter()+ "\u001B[0m" + "   ");
            }
            System.out.println();
        }

    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printGameRules(){
        System.out.println("\033[4;31m" + "  Game rules:  \n" + "\u001B[0m" + " Each ship must be placed horizontally or vertically across grid spaces—not diagonally—\n " +
                "The ships can't hang off the grid.\n " +
                "Ships can touch each other, but they can't occupy the same grid space.\n " +
                "You cannot change the position of the ships after the game begins.");
    }

    public void printMenu() {
        System.out.println(" (1) Manual placement\n (2) Random placement \n (3) Exit game");
    }

}