package com.codecool.game;

import com.codecool.board.Board;
import com.codecool.board.Square;
import com.codecool.board.enums.SquareStatuses;
import com.codecool.player.Player;
import com.codecool.util.Display;
import com.codecool.util.Input;

import java.util.Arrays;

public class Game {


    public void startGame() {
        Board board = new Board();
        Board board2 = new Board();
        Display display = new Display();
        Input input = new Input(display);
        Player player1 = new Player(display, input, "\033[1;96m"+"Player1"+"\u001B[0m");
        Player player2 = new Player(display, input, "\033[1;95m"+"Player2"+"\u001B[0m");
        boolean isPlayer1Turn = false;
        boolean startGame = true;
        boolean isPlayer1Set = false;
        boolean isPlayer2Set = false;

        display.printMessage("\n" +
                "  ____        _   _   _           _     _       \n" +
                " |  _ \\      | | | | | |         | |   (_)      \n" +
                " | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __  \n" +
                " |  _ < / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\ \n" +
                " | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |\n" +
                " |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/ \n" +
                "                                         | |    \n" +
                "                                         |_|    \n");

        display.printMessage("                __/___            \n" +
                "          _____/______|           \n" +
                "  _______/_____\\_______\\_____     \n" +
                "  \\              < < <       |    \n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        display.printGameRules();
        while(!isPlayer1Set) {
            System.out.println(player1.getName());
            input.getOption(player1, board);
            display.displayBoard(board.getBoard());
//            input.getUserInput(player1, board);
            isPlayer1Set = true;
        }
        while (!isPlayer2Set) {
            System.out.println(player2.getName());
            input.getOption(player2, board2);
            display.displayBoard(board2.getBoard());
//            input.getUserInput(player2, board2);
            isPlayer2Set = true;
        }
        while (startGame) {
            isPlayer1Turn = !isPlayer1Turn;
            if (isPlayer1Turn) {
                //muta jucatorul 1
                playRound(board2, display, input, player1, player2);
            } else {
                //muta jucatorul 2
                playRound(board, display, input, player2, player1);
            }
            if(checkWin(board) || checkWin(board2)) {
                display.printMessage(checkWin(board) ? "\033[1;95m" + "Player 2 wins!" + " \u001B[0m" : "\033[1;95m" + "Player 1 wins!" + " \u001B[0m");
                startGame = false;
            }
        }

    }

    private void playRound(Board board2, Display display, Input input, Player player1, Player player2) {
        System.out.println();
        System.out.println();
        System.out.println();
        display.printMessage(player1.getName() + " is shooting");
        display.displayBoardForShooting(board2.getBoard());
        input.handleInputForShooting(board2.getBoard(), player2, player1);
        player2.markSunk(board2.getBoard());
        display.displayBoardForShooting(board2.getBoard());
    }


    public boolean checkWin(Board board) {
        int numberOfShips = 5;
        for (Square[] row : board.getBoard()) {
            if (Arrays.stream(row).map(Square::getSquareStatuses).anyMatch(SquareStatuses.DEAD::equals)) {
                numberOfShips--;
            }
        }
        if(numberOfShips == 0){
            return true;
        }
        return false;
    }



}