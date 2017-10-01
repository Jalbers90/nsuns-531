package com.jalbers.nsunstest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Spinner roundingSpinner;
    Switch screenSleepSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        roundingSpinner = (Spinner) findViewById(R.id.roundingSpinner);
        String[] items = new String[] {"5", "2.5"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items);

        roundingSpinner.setAdapter(spinnerAdapter);

        roundingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    MainActivity.catherineMartin = true;
                    ((TextView) view).setTextColor(Color.WHITE);

                } else if (i == 1) {
                    MainActivity.catherineMartin = false;
                    ((TextView) view).setTextColor(Color.WHITE);

                }

                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("com.jalbers.nsunstest", Context.MODE_PRIVATE);
                sharedPref.edit().putInt("spinner_item", i).apply();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("com.jalbers.nsunstest", Context.MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("spinner_item",-1);
        if(spinnerValue != -1) {
            // set the value of the spinner
            roundingSpinner.setSelection(spinnerValue, true);
        }
    }

    public void toHome (View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    /////  MENU CREATION //////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.homeMenu:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    /////////// END MENU CREATION ///////////////////////////////////////
}
