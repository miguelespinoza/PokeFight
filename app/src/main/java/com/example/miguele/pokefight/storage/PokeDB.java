package com.example.miguele.pokefight.storage;

import android.content.Context;

import com.example.miguele.pokefight.model.PokeFighter;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Resources used:
 *  - Realm:
 *
 */
public class PokeDB {

    private PokeDB() {}

    public static void addFighter(Context context, PokeFighter pokemon) {
        Realm realm = Realm.getInstance(context);

        realm.beginTransaction();
        PokeFighter pokeFighter = realm.copyToRealm(pokemon);
        realm.commitTransaction();
    }

    public static PokeFighter getFighter(Context context) {
        Realm realm = Realm.getInstance(context);

        realm.beginTransaction();
        RealmQuery<PokeFighter> query = realm.where(PokeFighter.class);
        PokeFighter pokeFighter = query.findFirst();
        realm.commitTransaction();

        return pokeFighter;
    }
}
