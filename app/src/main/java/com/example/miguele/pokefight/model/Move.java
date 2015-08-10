package com.example.miguele.pokefight.model;

/**
 * Created by miguele on 8/9/15.
 */
public class Move {
    private String name;
    private int power;
    private int pp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }
}
