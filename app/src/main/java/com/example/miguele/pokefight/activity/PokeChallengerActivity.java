package com.example.miguele.pokefight.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.miguele.pokefight.R;
import com.example.miguele.pokefight.adapters.ChallengerListAdapter;
import com.example.miguele.pokefight.adapters.DividerItemDecoration;
import com.example.miguele.pokefight.adapters.PokeLayoutManager;
import com.example.miguele.pokefight.adapters.RecyclerItemClickListener;
import com.example.miguele.pokefight.storage.SyncInfo;
import com.example.miguele.pokefight.utils.Spitter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PokeChallengerActivity extends ActionBarActivity {
    private static final String TAG = "activity_poke_challenger";

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.challenger_list_rv) RecyclerView mChallengerRecyclerView;
    @Bind(R.id.poke_list_fab) FloatingActionButton mPokeFab;

    private TreeMap<String, String> mChallengers = null;
    private ArrayList<String> mChallengerNames = null; // For onClick event
    private String selectedChallenger;
    private PokeLayoutManager pokeLayoutManager;
    private ChallengerListAdapter challengersAdapter;
    private Firebase challengerFirebase = null;

    private void init() {
        mChallengers = new TreeMap<>();
        mChallengerNames = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_challenger);
        ButterKnife.bind(this);


        Spitter.Log(TAG, "onCreate", "starting ChallengerActivity");
        init();
        setupUI();
        setupFirebase();
        createChallengerList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poke_challenger, menu);
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
        this.mChallengerRecyclerView.setLayoutManager(pokeLayoutManager);
        this.mChallengerRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        this.mChallengerRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PokeChallengerActivity.this.mPokeFab.setVisibility(View.VISIBLE);
                PokeChallengerActivity.this.mPokeFab.show();
                selectedChallenger = mChallengers.get(mChallengerNames.get(position));
                Spitter.Log(TAG, "onClickList", mChallengers.get(mChallengerNames.get(position)) + " was selected");
            }
        }));

        this.mPokeFab.hide();
    }

    // Initialize toolbar
    public void initializeToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle("Choose Challenger");
            mToolbar.setBackgroundColor(getResources().getColor(R.color.primaryBlue500));

            setSupportActionBar(mToolbar);
        }
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        challengerFirebase = new Firebase("https://poke-testing.firebaseio.com/");
    }

    public void renderChallengerList(ArrayList<String> challengers) {
        // mChallenger is all set
        Spitter.Log(TAG, "renderChallengerList", "starting");
        if (challengers != null) {
            if (this.challengersAdapter == null) {
                this.challengersAdapter = new ChallengerListAdapter(this, challengers);
            } else {
                this.challengersAdapter.setChallengers(challengers);

            }
            this.mChallengerRecyclerView.setAdapter(this.challengersAdapter);
        }
    }

    // createChallengerList : GET challengers from database and set up in RecyclerView
    private void createChallengerList() {
        Spitter.Log(TAG, "createChallengerList", "just starting!");

        // Create a onetime Retrieve Listener
        // Create reference to users database..
        Firebase usersRef = challengerFirebase.child("users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()) {
                    mChallengers.put((String) child.child("name").getValue(), child.getKey());
                }

//                ArrayList<String> names = new ArrayList<>();
                for(Map.Entry<String,String> entry : mChallengers.entrySet()) {


                    if (SyncInfo.getYourName(PokeChallengerActivity.this).equals(entry.getKey())) {
                        Spitter.Log(TAG, "onBindViewHolder--yourName", "avoiding yourname");
                        continue;
                    }
                    mChallengerNames.add(entry.getKey());
                }

                renderChallengerList(mChallengerNames);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Spitter.Error(TAG, "createChallengerList", firebaseError.toString());
            }
        });
    }
}
