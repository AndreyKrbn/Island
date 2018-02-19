package com.company;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import static java.lang.Runtime.*;

public class IslandField {
    private int size;
    private IslandCell[][] island;


    //Реализовать через события, сделать privet
    public int wolfCount;
    public int rabbitCount;

    public void Initialise() {
        island = new IslandCell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                island[i][j] = new IslandCell(this);
            }
        }
        Random r = new Random();
        for (int i = wolfCount; i > 0; i--) {
            int x = r.nextInt(size);
            int y = r.nextInt(size);
            if (island[x][y].getVisitors().size() < 1) {
                island[x][y].getVisitors().add(new Wolf(String.valueOf(i)));
            } else {
                i++;
            }
        }
        for (int i = rabbitCount; i > 0; i--) {
            int x = r.nextInt(size);
            int y = r.nextInt(size);
            if (island[x][y].getVisitors().size() < 1) {
                island[x][y].getVisitors().add(new Rabbit(String.valueOf(i)));
            } else {
                i++;
            }
        }
    }

    public IslandField(int size, int wolfCount, int rabbitCount){
        this.size = size;
        this.wolfCount = wolfCount;
        this.rabbitCount = rabbitCount;
    }

    public boolean EndOfSystem(){
        boolean existWolf = false;
        boolean existRabbit = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (Animal animal : island[i][j].getVisitors()) {
                    if ((!existWolf) && (animal instanceof Wolf)) existWolf = true;
                    if ((!existRabbit) && (animal instanceof Rabbit)) existRabbit = true;
                    if (existWolf && existRabbit) {return false;}
                }
            }
        }
        return true;
    }

  public void LifeOfDay(){
      for (int i = 0; i < size; i++) {
          for (int j = 0; j < size; j++) {
              island[i][j].LifeOfDay();
          }
      }
  }

    public void MoveOfDay() throws Exception {
        Animal animal = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Iterator<Animal> iter = island[i][j].getVisitors().iterator();
                while (iter.hasNext()) {
                    animal = iter.next();
                    if (animal.isMoved()) { continue;
                    }
                    switch (animal.GetDirection()) {
                        case Top:
                            if ((i-1) >= 0 && (island[i - 1][j].getVisitors().size()<2 || (animal instanceof Wolf))) {
                                iter.remove();
                                island[i - 1][j].getVisitors().add(animal);
                            }
                            break;
                        case Left:
                            if ((j-1) >= 0 && (island[i][j - 1].getVisitors().size()<2|| (animal instanceof Wolf))) {
                                iter.remove();
                                island[i][j - 1].getVisitors().add(animal);
                            }
                            break;
                        case Right:
                            if ((j+1) < this.size && (island[i][j + 1].getVisitors().size()<2|| (animal instanceof Wolf))) {
                                iter.remove();
                                island[i][j + 1].getVisitors().add(animal);
                            }
                            break;
                        case Bottom:
                            if ((i+1) < this.size && (island[i + 1][j].getVisitors().size()<2|| (animal instanceof Wolf))) {
                                iter.remove();
                                island[i + 1][j].getVisitors().add(animal);
                            }
                            break;
                    }
                }
            }
        }
    }

    public void Display(){
        String cell;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                System.out.print(((j==0) ? "|": "") + island[i][j].Paint() +"|");
            }
             System.out.println();
        }
        //Реализовать через события
        System.out.printf("Count of Wolf: %d; Count of Rabbit: %d", wolfCount, rabbitCount);
        System.out.println();

    }
    public void clearDisplay() {
        for(int i = 0; i < 100; i++) {System.out.println();}
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
//        try {
//            getRuntime().exec("clear");
//        } catch (IOException e) {
            //e.printStackTrace();
//        }
    }
}
