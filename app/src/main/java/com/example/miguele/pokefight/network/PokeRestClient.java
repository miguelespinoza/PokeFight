package com.example.miguele.pokefight.network;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import io.realm.RealmObject;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

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
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();



        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();

        REST_CLIENT = adapter.create(PokeApi.class);
    }
}
