package com.example.miguele.pokefight.network;

import com.example.miguele.pokefight.model.Move;
import com.example.miguele.pokefight.model.Pokemon;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by miguele on 8/9/15.
 */
public interface PokeApi {

    @GET("/pokemon/{id}")
    void getPokemon(@Path("id") String id, Callback<Pokemon> callback);

    @GET("/move/{id")
    void getPokemonMove(@Path("id") String id, Callback<Move> callback);
}
