package com.codecool.board;

import com.codecool.util.Display;
import com.codecool.util.Input;

public class BoardFactory extends Board {
    private Display display;
    private Input input;


    public BoardFactory(Display display, Input input) {
        this.display = display;
        this.input = input;
    }


    public void randomPlacement() {

    }


}
