package com.codecool.player;

import com.codecool.board.Board;
import com.codecool.board.Ship;
import com.codecool.board.Square;
import com.codecool.util.Display;
import com.codecool.util.Input;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Ship> listOfShips;
    protected String name;
    protected Input input;
    protected Display display;

    public Player (Display display, Input input, String name) {
        this.name = name;
        this.display = display;
        this.input = input;
        this.listOfShips = new ArrayList<>();
    }

    public void addShips(Ship ship) {
        listOfShips.add(ship);
    }

    public void handleShoot(Board playerBoard, Board enemyBoard) {

    }
    public boolean isAlive() {
        for (Ship ship : listOfShips) {
            System.out.println(ship);
        }
        return false;
    }

    public List<Ship> getListOfShips() {
        return listOfShips;
    }

    public String getName() {
        return name;
    }

    public void markSunk(Square[][] board) {
        for (Ship ship : listOfShips) {
            if (!ship.isSunk()){
                ship.checkIfSunk();
                if(ship.isSunk()){
                    ship.markSunk(board);
                }
            }

        }
    }

    public void markHit(int row, int col) {
        for (Ship ship : listOfShips) {
            ship.markHit(row, col);
        }
    }
}
