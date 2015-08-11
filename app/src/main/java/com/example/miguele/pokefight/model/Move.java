package com.example.miguele.pokefight.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by miguele on 8/9/15.
 */
public class Move extends RealmObject {

    @SerializedName("name") private String name;
    @SerializedName("power") private int power;
    @SerializedName("pp") private int pp;

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
