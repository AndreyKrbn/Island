package com.company;

public class Rabbit extends Animal {

    Rabbit(String name)
    {
        super(name);
    }

    @Override
    public String DisplayInfo() {
        //return "R"+getName();
        return "R";
    }
}
