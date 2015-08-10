package com.example.miguele.pokefight.model;

import java.util.ArrayList;

/**
 * Created by miguele on 8/9/15.
 */
public class Pokemon {

    private String name;
    private int pkdx_id;
    private int attack;
    private int defense;
    private ArrayList<Moves> moves;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPkdx_id() {
        return pkdx_id;
    }

    public void setPkdx_id(int pkdx_id) {
        this.pkdx_id = pkdx_id;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;

    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public ArrayList<Moves> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Moves> moves) {
        this.moves = moves;
    }


}
