package com.codecool.board;

import com.codecool.board.enums.ShipOrientation;
import com.codecool.board.enums.ShipType;
import com.codecool.board.enums.SquareStatuses;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<Square> listOfParts;
    private ShipType shipType;
    private ShipOrientation shipOrientation;
    private int x;
    private int y;
    private boolean sunk;


    public Ship(ShipType shipType, int x, int y, ShipOrientation shipOrientation) {
        this.shipType = shipType;
        this.x = x;
        this.y = y;
        this.shipOrientation = shipOrientation;
        this.listOfParts = new ArrayList<>();
        addShipsParts();

    }


    public void addShipsParts() {
        if (shipOrientation.equals(ShipOrientation.VERTICAL)) {
            for (int i = 0; i < shipType.getLengthOfShip(); i++) {
                Square shipPart = new Square(this.x + i, this.y, SquareStatuses.SHIP);
                listOfParts.add(shipPart);
            }

        }else {
            for (int i = 0; i <shipType.getLengthOfShip(); i++) {
                Square shipPart = new Square(this.x, this.y + i, SquareStatuses.SHIP);
                listOfParts.add(shipPart);
            }
        }

    }

    public void placeShip(Square[][] gameBoard) {
        for (int i = 0; i < shipType.getLengthOfShip(); i++) {
            if (this.shipOrientation == ShipOrientation.HORIZONTAL) {
                gameBoard[this.x][this.y + i] = new Square(x, y, SquareStatuses.SHIP);
            }
            else
                gameBoard[this.x + i][this.y] = new Square(x, y, SquareStatuses.SHIP);
        }
    }

    public void checkIfSunk() {
        sunk = true;
        for (Square part : listOfParts) {
            if (!part.getSquareStatuses().equals(SquareStatuses.HIT)) {
                sunk = false;
            }
        }

    }

    public List<Square> getListOfShips() {
        return listOfParts;
    }

    public void setShipOrientation(ShipOrientation shipOrientation) {
        this.shipOrientation = shipOrientation;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void markSunk(Square[][] board) {
        for(Square part : listOfParts) {
            board[part.getX()][part.getY()].setSquareStatuses(SquareStatuses.DEAD);
        }
    }

    public void markHit(int row, int col) {
        for (Square part : listOfParts) {
            if (part.getX() == row && part.getY() == col) {
                part.setSquareStatuses(SquareStatuses.HIT);
            }
        }
    }
}