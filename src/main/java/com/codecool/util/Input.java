package com.codecool.util;

import com.codecool.board.Board;
import com.codecool.board.Ship;
import com.codecool.board.Square;
import com.codecool.board.enums.ShipOrientation;
import com.codecool.board.enums.ShipType;
import com.codecool.board.enums.SquareStatuses;
import com.codecool.player.Player;

import java.util.Random;
import java.util.Scanner;

public class Input {
    private Display display;
    Scanner scanner = new Scanner(System.in);

    public Input(Display display) {
        this.display = display;
    }

    public void getUserInput(Player player, Board board) {
        int counter = 0;
        int shipLength = 5;
        while (counter <= ShipType.values().length - 1) {

            display.displayBoard(board.getBoard());
            display.printMessage("Enter the coordinates for your "
                    + ShipType.values()[counter].getName() + " ship"
                    + " (" + ShipType.values()[counter].getLengthOfShip()
                    + " tiles length)[row][col][v/o]");
            String userInput = scanner.nextLine();
            if (userInput.equals("3")) {
                display.printMessage("\033[0;94m" + "Goodbye!" + "\u001B[0m");
                System.exit(0);
            }
            int row = parseIntRow(userInput);
            int col = parseIntCol(userInput);
            String direction = parseDirection(userInput);


            if ((col == -1 || row == -1 || direction.equals("false")) || (row + 1 > board.getBoard().length) || (row + shipLength > board.getBoard().length && direction.equals("v")) || (col + shipLength > board.getBoard().length && direction.equals("o")) || (col + 1 > board.getBoard().length) || (board.getBoard()[row][col].getSquareStatuses() == SquareStatuses.SHIP)) {
                display.printMessage("Invalid coordinates");
                continue;
            }

            //check in sus orizontala si //check in jos orizontala
            if (chechSusOrizontala(row, col, shipLength, direction, board) || checkJosOrizontala(row, col, shipLength, direction, board)) {
                display.printMessage("Invalid coordinates");
                continue;
            }

            // check la dreapta orizontala
            if ((col + shipLength < board.getBoard().length && direction.equals("o"))) {
                if (board.getBoard()[row][col + shipLength].getSquareStatuses() == SquareStatuses.SHIP) {
                    System.out.println("primul");
                    display.printMessage("Invalid coordinates");
                    continue;
                }
                System.out.println("You placed a ship");
            }

            // check la stanga orizontala
            if ((col - shipLength > 0 && direction.equals("o"))) {
                if (board.getBoard()[row][col - 1].getSquareStatuses() == SquareStatuses.SHIP) {
                    System.out.println("check stanga orizontala");
                    display.printMessage("Invalid coordinates");
                    continue;
                }
                System.out.println("You placed a ship check s oriz");
            }

            // check sus verticala
            if(row - 1 > 0 && direction.equals("v")){
                if(board.getBoard()[row-1][col].getSquareStatuses() == SquareStatuses.SHIP){
                    display.printMessage("Invalid coordinates");
                    continue;
                }
            }

            // check jos verticala
            if(row + shipLength < board.getBoard().length && direction.equals("v")){
                if (board.getBoard()[row + shipLength][col].getSquareStatuses() == SquareStatuses.SHIP){
                    display.printMessage("Invalid coordinates");
                    continue;
                }
            }

            //check stanga verticala si dreapta verticala
            if(checkStangaVerticala(row, col, shipLength, direction, board) || checkDreaptaVertivala(row, col, shipLength, direction, board)){
                display.printMessage("Invalid coordinates");
                continue;
            }

            Ship ship = new Ship(ShipType.values()[counter], row, col, direction.equals("o") ? ShipOrientation.HORIZONTAL : ShipOrientation.VERTICAL);
            System.out.println("esti bine");
            player.addShips(ship);
            ship.placeShip(board.getBoard());

            counter++;
            shipLength--;
        }
    }


    public void randomPlacement(Player player, Board board) {
        int counter = 0;
        int shipLength = 5;
        Random random = new Random();
        while (counter <= ShipType.values().length - 1) {
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            String direction = random.nextInt(2) == 0 ? "o" : "v";


            if ( (row + 1 > board.getBoard().length) || (row + shipLength > board.getBoard().length && direction.equals("v")) || (col + shipLength > board.getBoard().length && direction.equals("o")) || (col + 1 > board.getBoard().length) || (board.getBoard()[row][col].getSquareStatuses() == SquareStatuses.SHIP)) {

                continue;
            }

            //check in sus orizontala si //check in jos orizontala
            if (chechSusOrizontala(row, col, shipLength, direction, board) || checkJosOrizontala(row, col, shipLength, direction, board)) {

                continue;
            }

            // check la dreapta orizontala
            if ((col + shipLength < board.getBoard().length && direction.equals("o"))) {
                if (board.getBoard()[row][col + shipLength].getSquareStatuses() == SquareStatuses.SHIP) {

                    continue;
                }

            }

            // check la stanga orizontala
            if ((col - shipLength > 0 && direction.equals("o"))) {
                if (board.getBoard()[row][col - 1].getSquareStatuses() == SquareStatuses.SHIP) {

                    continue;
                }

            }

            // check sus verticala
            if(row - 1 > 0 && direction.equals("v")){
                if(board.getBoard()[row-1][col].getSquareStatuses() == SquareStatuses.SHIP){

                    continue;
                }
            }

            // check jos verticala
            if(row + shipLength < board.getBoard().length && direction.equals("v")){
                if (board.getBoard()[row + shipLength][col].getSquareStatuses() == SquareStatuses.SHIP){

                    continue;
                }
            }

            //check stanga verticala si dreapta verticala
            if(checkStangaVerticala(row, col, shipLength, direction, board) || checkDreaptaVertivala(row, col, shipLength, direction, board)){

                continue;
            }
            Ship ship = new Ship(ShipType.values()[counter], row, col, direction.equals("o") ? ShipOrientation.HORIZONTAL : ShipOrientation.VERTICAL);
            player.addShips(ship);
            ship.placeShip(board.getBoard());

            counter++;
            shipLength--;

        }

    }

    public int parseIntRow(String input) {

        int row = Integer.parseInt(String.valueOf(Character.toUpperCase(input.charAt(0)) - 65));
        if (Character.isLetter(input.charAt(0)) && row < 10) {
            return row;
        }
        return -1;
    }


    public int parseIntCol(String input) {
        if (input.length() == 3 && input.substring(1, 2).matches("\\d+") && Integer.parseInt(input.substring(1, 2)) - 1 < 10)
            return Integer.parseInt(input.substring(1, 2)) - 1;

        else if (input.length() == 4 && input.substring(1, 3).matches("\\d+") && Integer.parseInt(input.substring(1, 3)) - 1 < 10)
            return Integer.parseInt(input.substring(1, 3)) - 1;
        else
            return -1;
    }

    public int parseIntColShoot(String input) {
        if (input.length() == 2 && input.substring(1, 2).matches("\\d+") && Integer.parseInt(input.substring(1, 2)) - 1 < 10)
            return Integer.parseInt(input.substring(1, 2)) - 1;

        else if (input.length() == 3 && input.substring(1, 3).equals("10") && Integer.parseInt(input.substring(1, 3)) - 1 < 10)
            return Integer.parseInt(input.substring(1, 3)) - 1;
        else
            return -1;
    }

    public String parseDirection(String input) {
        if (input.length() == 3 && (input.charAt(2) == 'o' || input.charAt(2) == 'v')) {
            return input.substring(2);
        } else if (input.length() == 4 && (input.charAt(3) == 'o' || input.charAt(3) == 'v')) {
            return input.substring(3);
        }
        return "false";
    }


    public int getOption(Player player, Board board) {
        String userInput = "";
        boolean firstTry = true;
        do {
            display.printMessage(firstTry ? "Main Menu\nSelect option: " : "Invalid option, try again");
            display.printMenu();
            firstTry = false;
            userInput = scanner.nextLine();

            if (userInput.equals("1")) {
                getUserInput(player, board);
            } else if (userInput.equals("2")) {
                randomPlacement(player, board);
            } else if (userInput.equals("3")) {
                display.printMessage("\033[0;94m" + "Goodbye!" + "\u001B[0m");
                System.exit(0);
            }
        } while (!validateOption(userInput));
        return Integer.parseInt(userInput);

    }

    public boolean validateOption(String input) {
        if (input.equals("1") || input.equals("2") || input.equals("3"))
            return true;
        return false;
    }

    public void handleInputForShooting(Square[][] board, Player enemyPlayer, Player currentPlayer) {
        String coordinates = "";
        while (true) {
            display.printMessage("Where do you want to shoot?");
            coordinates = scanner.nextLine();
            if (coordinates.equals("3")) {
                display.printMessage("\033[0;94m" + "Goodbye!" + "\u001B[0m");
                System.exit(0);
            }

            int row = parseIntRow(coordinates);
            int col = parseIntColShoot(coordinates);

            if (coordinates.length() > 3 || coordinates.length() < 1 || row == -1 || col == -1) {
                display.printMessage("Invalid coordinates");
                continue;
            }

            if (board[row][col].getSquareStatuses() == SquareStatuses.SHIP) {
                board[row][col].setSquareStatuses(SquareStatuses.HIT);
                display.printMessage(currentPlayer.getName() + ", you hit a ship");
                enemyPlayer.markHit(row, col);
                break;
            } else if (board[row][col].getSquareStatuses() == SquareStatuses.HIT) {
                display.printMessage(currentPlayer.getName() + ", you already hit this place");
            } else if (board[row][col].getSquareStatuses() == SquareStatuses.EMPTY) {
                board[row][col].setSquareStatuses(SquareStatuses.MISSED);
                display.printMessage(currentPlayer.getName() + ", you missed");
                break;
            }
        }

    }

    public boolean chechSusOrizontala(int row, int col, int shipLength, String direction, Board board) {
        boolean isChecked = false;

        if (row - 1 >= 0 && direction.equals("o")) {
            for (int num = 0; num < shipLength; num++) {
                if (board.getBoard()[row - 1][col + num].getSquareStatuses() == SquareStatuses.SHIP) {

                    isChecked = true;
                }
            }

        }

        return isChecked;
    }

    public boolean checkJosOrizontala(int row, int col, int shipLength, String direction, Board board){
        boolean isChecked = false;
        if(row + 1 < board.getBoard().length && direction.equals("o")){
            for(int num = 0; num < shipLength; num++){
                if(board.getBoard()[row+1][col+num].getSquareStatuses() == SquareStatuses.SHIP){
                    isChecked = true;
                }
            }
        }
        return isChecked;
    }

    public boolean checkStangaVerticala(int row, int col, int shipLength, String direction, Board board){
        boolean isChecked = false;
        if(col - 1 >= 0 && direction.equals("v")){
            for(int num = 0; num < shipLength; num++){
                System.out.println(shipLength);
                if(board.getBoard()[row+num][col-1].getSquareStatuses() == SquareStatuses.SHIP){

                    isChecked = true;
                }
            }
        }
        return isChecked;
    }

    public boolean checkDreaptaVertivala(int row, int col, int shipLength, String direction, Board board){
        boolean isChecked = false;
        if(col + 1 < board.getBoard().length && direction.equals("v")){
            for (int num = 0; num < shipLength; num++) {
                if(board.getBoard()[row+num][col+1].getSquareStatuses() == SquareStatuses.SHIP){
                    isChecked = true;
                }
            }
        }
        return isChecked;
    }


}