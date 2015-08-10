package com.example.miguele.pokefight.network;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Resources used:
 *  - Retrofit:
 *      - http://inaka.net/blog/2014/10/10/android-retrofit-rest-client/
 *      - http://square.github.io/retrofit/
 *
 *  - Future implementation:
 *      - http://joluet.github.io/blog/2014/07/07/rxjava-retrofit/
 */
public class PokeRestClient {

    private static PokeApi REST_CLIENT;
    private static String ROOT = "http://pokeapi.co/api/v1";

    static {
        setupRestClient();
    }
    private PokeRestClient() {}

    public static PokeApi get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        REST_CLIENT = adapter.create(PokeApi.class);
    }
}
