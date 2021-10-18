package com.codecool.game;

import com.codecool.board.Board;
import com.codecool.board.Ship;
import com.codecool.player.Player;
import com.codecool.util.Display;
import com.codecool.util.Input;

import java.util.Scanner;

public class Battleship {

    Display display = new Display();
    Input input = new Input(display);

    public void start() {
        Game game = new Game();
        game.startGame();
    }


}

