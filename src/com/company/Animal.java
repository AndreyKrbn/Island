package com.company;

import java.util.Random;

enum Direction {
    Top,
    Left,
    Right,
    Bottom
}

public abstract class Animal {
    private String Name;

    public String getName() {
        return Name;
    }

    public boolean isMoved() {
        return moved;
    }
//Новорожденные пропускают ход
    private boolean moved = true;

    Animal(String name) {
        this.Name = name;
    }

    //Случайное направление
    public Direction GetDirection() {
        Random r = new Random();
        moved = true;
        return Direction.values()[r.nextInt(4)];
        //return Direction.values()[1];
    }
    public void Rest(){
        moved = false;
    }

    public abstract String DisplayInfo();
}
