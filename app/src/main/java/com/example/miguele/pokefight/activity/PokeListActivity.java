package com.example.miguele.pokefight.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.miguele.pokefight.R;
import com.example.miguele.pokefight.adapters.DividerItemDecoration;
import com.example.miguele.pokefight.adapters.PokeLayoutManager;
import com.example.miguele.pokefight.adapters.PokeListAdapter;
import com.example.miguele.pokefight.adapters.RecyclerItemClickListener;
import com.example.miguele.pokefight.model.Move;
import com.example.miguele.pokefight.model.PokeFighter;
import com.example.miguele.pokefight.model.Pokemon;
import com.example.miguele.pokefight.network.PokeRestClient;
import com.example.miguele.pokefight.storage.PokeDB;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Resources used:
 *
 */
public class PokeListActivity extends ActionBarActivity {

    private static final String TAG = "activity_poke_list";


    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.poke_list_rv) RecyclerView mPokeRecyclerView;
    @Bind(R.id.poke_list_fab) FloatingActionButton mPokeFab;

    private ArrayList<Pokemon> mPokeList;
    private Pokemon selectedPoke;
    private PokeLayoutManager pokeLayoutManager;
    private PokeListAdapter pokeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_list);
        ButterKnife.bind(this);

        setupUI();

        createPokeList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poke_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupUI() {
        initializeToolbar();

        this.pokeLayoutManager = new PokeLayoutManager(this);
        this.mPokeRecyclerView.setLayoutManager(pokeLayoutManager);
        this.mPokeRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        this.mPokeRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PokeListActivity.this.mPokeFab.setVisibility(View.VISIBLE);
                PokeListActivity.this.mPokeFab.show();
                selectedPoke = mPokeList.get(position);
            }
        }));

        this.mPokeFab.hide();
    }

    // Initialize toolbar
    public void initializeToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle(R.string.app_name);
            mToolbar.setBackgroundColor(getResources().getColor(R.color.primaryBlue500));

            setSupportActionBar(mToolbar);
        }
    }

    public void renderPokeList(ArrayList<Pokemon> pokeList) {
        mPokeList = pokeList;
        Log.i(TAG, "renderPokeList");
        if (pokeList != null) {
            if (this.pokeAdapter == null) {
                this.pokeAdapter = new PokeListAdapter(this, pokeList);
            } else {
                this.pokeAdapter.setPokemonList(pokeList);
            }

            this.mPokeRecyclerView.setAdapter(pokeAdapter);
        }
    }

    /** Collecting information from http://pokeapi.co/ **/



    // createPokeList : create and generate Pokemon information
    private void createPokeList() {
        selectedPoke = new Pokemon();
        // Generate 10 random pokemon ids
        ArrayList<Integer> pokeReady = new ArrayList<Integer>(10);
        final ArrayList<Pokemon> pokeList = new ArrayList<Pokemon>(10);

        for (int i = 0; i < 10; i++) {
            int pokeNum = generatePokeId(pokeReady);
            pokeReady.add(pokeNum);

            PokeRestClient.get().getPokemon(String.valueOf(pokeNum), new Callback<Pokemon>() {
                @Override
                public void success(Pokemon pokemon, Response response) {
                    pokeList.add(pokemon);

                    if (pokeList.size() == 10) {
                        renderPokeList(pokeList);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.i(TAG, "function(createPokeList)\n" + error.toString());
                }
            });
        }
    }

    // generatePokeId : generate a pokemon id based on the range from 1 to 718
    private int generatePokeId(ArrayList<Integer> pokeReady) {
        int min = 1;
        int max = 718;
        int randomNum;

        boolean pokeAgain = false;
        do {
            Random random = new Random();
            randomNum = random.nextInt((max - min) + 1) + min;
            pokeAgain = false;
            for (int pokeNum : pokeReady) {
                if (pokeNum == randomNum) {
                    pokeAgain = true;
                }
            }
        } while(pokeAgain);

        return randomNum;
    }


    /** UI Implementation **/

    @OnClick(R.id.poke_list_fab)
    public void generatePokeFight() {
        generateMoves();    // TODO: will call intent since callback hell need to figure out how to do this better!! rxJava
    }

    /*
     * TODO: clean up callback hell!
     */
    public void generateMoves() {
        // generate 4 random nums for poke move ids
        ArrayList<Integer> randMovePos = new ArrayList<Integer>();
        int max = this.selectedPoke.getMoves().size();
        int min = 0;
        int randomNum;
        do {
            Random random = new Random();
            randomNum = random.nextInt((max - min) + 1) + min;
            for (int movePos : randMovePos) {
                if (movePos == randomNum) {
                    continue;
                }
            }

        } while (randMovePos.size() != 4);

        final ArrayList<Move> moves = new ArrayList<Move>();
        // now call the API to get the move info based on resource_uri
        for (int movePos : randMovePos) {
            String resource_uri = this.selectedPoke.getMoves().get(movePos).getResource_uri();
            PokeRestClient.get().getPokemonMove("331", new Callback<Move>() {
                @Override
                public void success(Move move, Response response) {
                    moves.add(move);

                    if (moves.size() == 4) {
                        // Save fighter
                        saveFighter(moves);

                        // Intent to PokeFight challenge
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.i(TAG, "function(createPokeList)\n" + error.toString());
                }
            });
        }

    }

    private void saveFighter(ArrayList<Move> moves) {
        Log.i(TAG, "func(saveFighter)");

        RealmList<Move> rMoves = new RealmList<Move>();
        for (Move move : moves) {
            rMoves.add(move);
        }

        Pokemon savePoke = this.selectedPoke;
        savePoke.setMoves(null);
        PokeFighter pokeFighter = new PokeFighter();
//        pokeFighter.setPokemon(savePoke);
        pokeFighter.setMoves(rMoves);

        PokeDB.addFighter(this, pokeFighter);

    }


    // Change FAB (Pause -> Proceed)
}
