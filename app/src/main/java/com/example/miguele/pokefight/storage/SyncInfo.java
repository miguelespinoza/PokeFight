package com.example.miguele.pokefight.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by miguele on 8/12/15.
 */
public class SyncInfo {

    private static final String STORE_CHALLENGER_NAME = "com.example.miguele.pokefight.storage.challenger_name";
    private static final String STORE_YOUR_NAME = "com.example.miguele.pokefight.storage.your_name";
    private static final String STORE_YOUR_ID = "com.example.miguele.pokefight.storage.your_id";
    private static final String STORE_POKE_RESOURCE = "com.example.miguele.pokefight.storage.poke_resource_uri";
    private static final String STORE_CHALLENGE_ID = "com.example.miguele.pokefight.storage.challenge_id";

    private static final String CHALLENGER_NAME = "challenger_name";
    private static final String YOUR_NAME = "your_name";
    private static final String YOUR_ID = "your_id";
    private static final String POKE_RESOURCE = "poke_resource_uri";
    private static final String CHALLENGE_ID = "challenge_id";

    private SyncInfo() {}

    public static void setChallengerName(Context context, String challenger) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_CHALLENGER_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CHALLENGER_NAME, challenger);
        editor.commit();


    }

    public static void setYourInfo(Context context, String name, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_YOUR_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(YOUR_NAME, name);
        editor.commit();

        sharedPreferences = context.getSharedPreferences(
                STORE_YOUR_ID,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(YOUR_NAME, id);
        editor.commit();
    }

    public static void setPokeResourceUri(Context context, String resource_uri) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_POKE_RESOURCE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POKE_RESOURCE, resource_uri);
        editor.commit();
    }

    public static void setChallengeId(Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_CHALLENGE_ID,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CHALLENGE_ID, id);
        editor.commit();


    }

    public static String getChallengerName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_CHALLENGER_NAME,
                Context.MODE_PRIVATE);
        String challengerName = sharedPreferences.getString(CHALLENGER_NAME, "");

        return challengerName;
    }

    public static String getYourName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_YOUR_NAME,
                Context.MODE_PRIVATE);
        String yourName = sharedPreferences.getString(YOUR_NAME, "");

        return yourName;
    }

    public static String getYourId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_YOUR_ID,
                Context.MODE_PRIVATE);
        String yourId = sharedPreferences.getString(YOUR_ID, "");

        return yourId;
    }

    public static String getPokeResourceUri(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_POKE_RESOURCE,
                Context.MODE_PRIVATE);
        String pokeResource = sharedPreferences.getString(POKE_RESOURCE, "");

        return pokeResource;
    }

    public static String getChallengeId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_CHALLENGE_ID,
                Context.MODE_PRIVATE);
        String challengeID = sharedPreferences.getString(CHALLENGE_ID, "");

        return challengeID;
    }
}
