package com.company;


import java.util.ArrayList;
import java.util.ListIterator;

public class IslandCell {
    public IslandCell(IslandField islandField) {
        this.ownerIslandField = islandField;
    }

    private IslandField ownerIslandField;

    public ArrayList<Animal> getVisitors() {
        return visitors;
    }
    private ArrayList<Animal> visitors = new ArrayList<>();

  private void Hunting() {
      Wolf w = null;
      ArrayList<Rabbit> rabbits = new ArrayList<>();
      for (Animal v : visitors) {
          if (w == null && v instanceof Wolf) {
              w = (Wolf)v;
          }
          if (v instanceof Rabbit) {
              rabbits.add((Rabbit)v);
          }
      }
      if (w != null) {
          for (Animal i : rabbits) {
              w.Eating();
              visitors.remove(i);
              ownerIslandField.rabbitCount--;
          }
      }
  }
//Сброс движения, Размножение, Питание
    private void Pullulation() {
       int countRabbit = 0;
        ListIterator<Animal> iter = visitors.listIterator();
        while (iter.hasNext()) {
            Animal v = iter.next();
            v.Rest();
            if (v instanceof Rabbit) {
                countRabbit++;
            }
            if ( v instanceof Wolf) {
                if (((Wolf)v).Metabolism()<=0) {
                    iter.remove();
                    ownerIslandField.wolfCount--;
                }
            }
        }
        if (countRabbit ==2) {
            iter.add(new Rabbit("newR"));
            ownerIslandField.rabbitCount++;
        }

    }

    public void LifeOfDay(){
        Hunting();
        Pullulation();
    }
//Количество не ходивших
    public int Size() throws Exception{
      int res=0;
        for (Animal v : visitors) {
            res += (!v.isMoved()) ? 1 : 0;
        }
        if (res>3) {throw new Exception("Превышено количество особей в ячейке");}
      return res;
    }

    public String Paint(){
      StringBuilder sb = new StringBuilder();
        for (Animal i: visitors) {
            sb.append(i.DisplayInfo());
        }
        sb.setLength(10);
//        for (int i =0; i< 10-sb.toString().length(); i++) {
//            sb.append(" ");
//        }

      return sb.toString();
    }

}
