package com.example.miguele.pokefight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.miguele.pokefight.R;
import com.example.miguele.pokefight.storage.SyncInfo;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jonathanfinerty.once.Once;

public class PokeRegister extends ActionBarActivity {

    private static final String TAG = "activity_poke_register";

    private Firebase challengerFirebase = null;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.challenger_et) EditText mChallengerEditText;
    @Bind(R.id.challenger_submit_btn) Button mChallengerSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Once.initialise(this);

        String firstTimeRegister = "firstTimeRegister";
        if (!Once.beenDone(Once.THIS_APP_INSTALL, firstTimeRegister)) {
            setContentView(R.layout.activity_poke_register);
            ButterKnife.bind(this);

            setupUI();
            setupFirebase();

            // Onced registered successfully
            Once.markDone(firstTimeRegister);

        } else {
            // Go to Choose challenger
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poke_register, menu);
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
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        challengerFirebase = new Firebase("https://poke-testing.firebaseio.com/");
    }

    // Initialize toolbar
    public void initializeToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle("Register");
            mToolbar.setBackgroundColor(getResources().getColor(R.color.primaryBlue500));

            setSupportActionBar(mToolbar);
        }
    }

    @OnClick(R.id.challenger_submit_btn)
    public void submitName() {
        Log.i(TAG, "submitName to Firebase");

        String name = mChallengerEditText.getText().toString();
        Firebase usersRef = challengerFirebase.child("users");
        Firebase newUserRef = usersRef.push();

        Map<String, String> user = new HashMap<>();
        user.put("name", name);
        user.put("status", "available");
        newUserRef.setValue(user);

        // Get the unique ID genereated by push()
        String userID = newUserRef.getKey();

        // Save this info for later...
        SyncInfo.setYourInfo(this, name, userID);

        // Intent to PokeFight select challenger
        Intent challengerIntent = new Intent(PokeRegister.this, PokeChallenger.class);
        PokeRegister.this.startActivity(challengerIntent);

        // Testing to see what we get for reading child
//        challengerFirebase.child("message").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
//            }
//
//            @Override
//            public void onCancelled(FirebaseError error) {
//            }
//        });
    }
}
