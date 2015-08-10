package com.example.miguele.pokefight.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by miguele on 8/10/15.
 */
public class PokeFighter extends RealmObject {

    @SerializedName("pokemon") private Pokemon pokemon;
    @SerializedName("moves") private ArrayList<Move> moves;

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }
}
