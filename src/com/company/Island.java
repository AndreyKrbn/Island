package com.company;

import java.util.Scanner;

public class Island {

    public static void main(String[] args) {

//        Scanner in = new Scanner(System.in);

        IslandField island = new IslandField(5, 3, 10);
        island.Initialise();
        island.clearDisplay();

//        island.Display();
//        in.next();
        int day=0;
        while (!island.EndOfSystem()) {
            day++;
            try {
                island.MoveOfDay();
                island.Display();
                //in.next();
                Thread.sleep(2000);
                island.LifeOfDay();
                island.clearDisplay();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        island.Display();
        System.out.printf("GameOver Total days = %d", day);
    }
}

