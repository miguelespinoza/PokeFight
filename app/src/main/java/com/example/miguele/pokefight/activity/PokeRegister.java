package com.example.miguele.pokefight.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.miguele.pokefight.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import jonathanfinerty.once.Once;

public class PokeRegister extends ActionBarActivity {

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

    // Initialize toolbar
    public void initializeToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle(R.string.app_name);
            mToolbar.setBackgroundColor(getResources().getColor(R.color.primaryBlue500));

            setSupportActionBar(mToolbar);
        }
    }
}
