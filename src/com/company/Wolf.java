package com.company;

public class Wolf extends Animal {

    Wolf(String name)
    {
        super(name);
    }

    private int energy  = 20;

    @Override
    public String DisplayInfo() {
        //return "W"+getName()+"("+energy+")";
        //return "W";
        StringBuilder sb = new StringBuilder();
        sb.append("W(");
        sb.append(String.format("%02d", energy));
        sb.append(')');
        return sb.toString();
    }

    public void Eating(){
        energy +=7;
        energy = (energy > 20) ? 20 : energy;
    }
    public int Metabolism(){
        return --energy;
    }
}
