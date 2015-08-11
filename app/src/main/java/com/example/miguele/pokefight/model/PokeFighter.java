package com.example.miguele.pokefight.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by miguele on 8/10/15.
 */
public class PokeFighter extends RealmObject {

//    @SerializedName("pokemon") private Pokemon pokemon;
    @SerializedName("name") private String name;
    @SerializedName("pkdx_id") private int pkdx_id;
    @SerializedName("attack") private int attack;
    @SerializedName("defense") private int defense;
    @SerializedName("moves") private RealmList<Move> moves;

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

    public RealmList<Move> getMoves() {
        return moves;
    }

    public void setMoves(RealmList<Move> moves) {
        this.moves = moves;
    }
}
