package com.example.miguele.pokefight.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.miguele.pokefight.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import jonathanfinerty.once.Once;

public class PokeChallenger extends ActionBarActivity {

    // register challenge views
    @Nullable
    @Bind(R.id.challenger_et) EditText mChallengerEditText;
    @Nullable
    @Bind(R.id.challenger_submit_btn) Button mChallengerSubmitBtn;

    // poke challengers
    @Nullable
    @Bind(R.id.challenger_list_rv) RecyclerView mChallengerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Once.initialise(this);
        String firstTimeRegister = "firstTime";
        if (!Once.beenDone(Once.THIS_APP_INSTALL, firstTimeRegister)) {
            setContentView(R.layout.activity_register_challenger);
            Once.markDone(firstTimeRegister);
        } else {
            setContentView(R.layout.activity_poke_challenger);
        }
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
}
