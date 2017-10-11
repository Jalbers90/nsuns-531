package com.jalbers.nsunstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WeekOverviewActivity extends AppCompatActivity {

    //UI REF SETUP //

    LinearLayout weekOverviewLayout;

    //Day 1
    EditText day1EditText1;
    EditText day1EditText2;
    EditText day1AccessoryEditText;

    //Day 2
    EditText day2EditText1;
    EditText day2EditText2;
    EditText day2AccessoryEditText;

    //Day 3
    EditText day3EditText1;
    EditText day3EditText2;
    EditText day3AccessoryEditText;

    //Day 4
    EditText day4EditText1;
    EditText day4EditText2;
    EditText day4AccessoryEditText;

    //Day 5
    EditText day5EditText1;
    EditText day5EditText2;
    EditText day5AccessoryEditText;

    //Day 6
    EditText day6EditText1;
    EditText day6EditText2;
    EditText day6AccessoryEditText;

    //Day 7
    EditText day7EditText1;
    EditText day7EditText2;
    EditText day7AccessoryEditText;

    Button saveButton;


    ///////////////////////////////////////////////////////////////////////////////////////////



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_overview);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_weekoverview);
        uiRefSetup();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("com.jalbers.nsunstest", Context.MODE_PRIVATE);
        int workoutState = sharedPref.getInt("workoutState", -1);

        if (workoutState == 0) fourDaySetup();
        else if (workoutState == 1) fiveDaySetup();
        else if (workoutState == 2) sixDayDeadliftSetup();
        else if (workoutState == 3) sixDaySquatSetup();
        else if (workoutState == 4) {
            allowEdit();
            customSetup();
        }

    }



    /////  MENU CREATION //////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.weekoverview_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("com.jalbers.nsunstest", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        switch (item.getItemId()) {

            /*
            case R.id.homeMenu:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            */

            case R.id.fourDayMenu:
                stopEdit();
                editor.putInt("workoutState", 0).apply();
                fourDaySetup();
                return true;
                //return true;

            case R.id.fiveDayMenu:
                stopEdit();
                editor.putInt("workoutState", 1).apply();
                fiveDaySetup();
                return true;

            case R.id.sixDaySquatMenu:
                stopEdit();
                editor.putInt("workoutState", 2).apply();
                sixDaySquatSetup();
                return true;

            case R.id.sixDayDeadliftMenu:
                stopEdit();
                editor.putInt("workoutState", 3).apply();
                sixDayDeadliftSetup();
                return true;

            case R.id.customMenu:
                allowEdit();
                editor.putInt("workoutState", 4).apply();
                customSetup();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /////////// END MENU CREATION ///////////////////////////////////////

    public void allowEdit () {

        day1EditText1.setFocusable(true); day1EditText1.setFocusableInTouchMode(true); day1EditText1.setClickable(true); day1EditText1.setCursorVisible(true);
        day1EditText2.setFocusable(true); day1EditText2.setFocusableInTouchMode(true); day1EditText2.setClickable(true); day1EditText2.setCursorVisible(true);
        day1AccessoryEditText.setFocusable(true); day1AccessoryEditText.setFocusableInTouchMode(true); day1AccessoryEditText.setClickable(true); day1AccessoryEditText.setCursorVisible(true);

        day2EditText1.setFocusable(true); day2EditText1.setFocusableInTouchMode(true); day2EditText1.setClickable(true); day2EditText1.setCursorVisible(true);
        day2EditText2.setFocusable(true); day2EditText2.setFocusableInTouchMode(true); day2EditText2.setClickable(true); day2EditText2.setCursorVisible(true);
        day2AccessoryEditText.setFocusable(true); day2AccessoryEditText.setFocusableInTouchMode(true); day2AccessoryEditText.setClickable(true); day2AccessoryEditText.setCursorVisible(true);

        day3EditText1.setFocusable(true); day3EditText1.setFocusableInTouchMode(true); day3EditText1.setClickable(true); day3EditText1.setCursorVisible(true);
        day3EditText2.setFocusable(true); day3EditText2.setFocusableInTouchMode(true); day3EditText2.setClickable(true); day3EditText2.setCursorVisible(true);
        day3AccessoryEditText.setFocusable(true); day3AccessoryEditText.setFocusableInTouchMode(true); day3AccessoryEditText.setClickable(true); day3AccessoryEditText.setCursorVisible(true);

        day4EditText1.setFocusable(true); day4EditText1.setFocusableInTouchMode(true); day4EditText1.setClickable(true); day4EditText1.setCursorVisible(true);
        day4EditText2.setFocusable(true); day4EditText2.setFocusableInTouchMode(true); day4EditText2.setClickable(true); day4EditText2.setCursorVisible(true);
        day4AccessoryEditText.setFocusable(true); day4AccessoryEditText.setFocusableInTouchMode(true); day4AccessoryEditText.setClickable(true); day4AccessoryEditText.setCursorVisible(true);

        day5EditText1.setFocusable(true); day5EditText1.setFocusableInTouchMode(true); day5EditText1.setClickable(true); day5EditText1.setCursorVisible(true);
        day5EditText2.setFocusable(true); day5EditText2.setFocusableInTouchMode(true); day5EditText2.setClickable(true); day5EditText2.setCursorVisible(true);
        day5AccessoryEditText.setFocusable(true); day5AccessoryEditText.setFocusableInTouchMode(true); day5AccessoryEditText.setClickable(true); day5AccessoryEditText.setCursorVisible(true);

        day6EditText1.setFocusable(true); day6EditText1.setFocusableInTouchMode(true); day6EditText1.setClickable(true); day6EditText1.setCursorVisible(true);
        day6EditText2.setFocusable(true); day6EditText2.setFocusableInTouchMode(true); day6EditText2.setClickable(true); day6EditText2.setCursorVisible(true);
        day6AccessoryEditText.setFocusable(true); day6AccessoryEditText.setFocusableInTouchMode(true); day6AccessoryEditText.setClickable(true); day6AccessoryEditText.setCursorVisible(true);

        day7EditText1.setFocusable(true); day7EditText1.setFocusableInTouchMode(true); day7EditText1.setClickable(true); day7EditText1.setCursorVisible(true);
        day7EditText2.setFocusable(true); day7EditText2.setFocusableInTouchMode(true); day7EditText2.setClickable(true); day7EditText2.setCursorVisible(true);
        day7AccessoryEditText.setFocusable(true); day7AccessoryEditText.setFocusableInTouchMode(true); day7AccessoryEditText.setClickable(true); day7AccessoryEditText.setCursorVisible(true);

    }

    public void stopEdit () {

        day1EditText1.setFocusable(false); day1EditText1.setFocusableInTouchMode(false); day1EditText1.setClickable(false); day1EditText1.setCursorVisible(false);
        day1EditText2.setFocusable(false); day1EditText2.setFocusableInTouchMode(false); day1EditText2.setClickable(false); day1EditText2.setCursorVisible(false);
        day1AccessoryEditText.setFocusable(false); day1AccessoryEditText.setFocusableInTouchMode(false); day1AccessoryEditText.setClickable(true); day1AccessoryEditText.setCursorVisible(false);

        day2EditText1.setFocusable(false); day2EditText1.setFocusableInTouchMode(false); day2EditText1.setClickable(false); day2EditText1.setCursorVisible(false);
        day2EditText2.setFocusable(false); day2EditText2.setFocusableInTouchMode(false); day2EditText2.setClickable(false); day2EditText2.setCursorVisible(false);
        day2AccessoryEditText.setFocusable(false); day2AccessoryEditText.setFocusableInTouchMode(false); day2AccessoryEditText.setClickable(false); day2AccessoryEditText.setCursorVisible(false);

        day3EditText1.setFocusable(false); day3EditText1.setFocusableInTouchMode(false); day3EditText1.setClickable(false); day3EditText1.setCursorVisible(false);
        day3EditText2.setFocusable(false); day3EditText2.setFocusableInTouchMode(false); day3EditText2.setClickable(false); day3EditText2.setCursorVisible(false);
        day3AccessoryEditText.setFocusable(false); day3AccessoryEditText.setFocusableInTouchMode(false); day3AccessoryEditText.setClickable(false); day3AccessoryEditText.setCursorVisible(false);

        day4EditText1.setFocusable(false); day4EditText1.setFocusableInTouchMode(false); day4EditText1.setClickable(false); day4EditText1.setCursorVisible(false);
        day4EditText2.setFocusable(false); day4EditText2.setFocusableInTouchMode(false); day4EditText2.setClickable(false); day4EditText2.setCursorVisible(false);
        day4AccessoryEditText.setFocusable(false); day4AccessoryEditText.setFocusableInTouchMode(false); day4AccessoryEditText.setClickable(false); day4AccessoryEditText.setCursorVisible(false);

        day5EditText1.setFocusable(false); day5EditText1.setFocusableInTouchMode(false); day5EditText1.setClickable(false); day5EditText1.setCursorVisible(false);
        day5EditText2.setFocusable(false); day5EditText2.setFocusableInTouchMode(false); day5EditText2.setClickable(false); day5EditText2.setCursorVisible(false);
        day5AccessoryEditText.setFocusable(false); day5AccessoryEditText.setFocusableInTouchMode(false); day5AccessoryEditText.setClickable(false); day5AccessoryEditText.setCursorVisible(false);

        day6EditText1.setFocusable(false); day6EditText1.setFocusableInTouchMode(false); day6EditText1.setClickable(false); day6EditText1.setCursorVisible(false);
        day6EditText2.setFocusable(false); day6EditText2.setFocusableInTouchMode(false); day6EditText2.setClickable(false); day6EditText2.setCursorVisible(false);
        day6AccessoryEditText.setFocusable(false); day6AccessoryEditText.setFocusableInTouchMode(false); day6AccessoryEditText.setClickable(false); day6AccessoryEditText.setCursorVisible(false);

        day7EditText1.setFocusable(false); day7EditText1.setFocusableInTouchMode(false); day7EditText1.setClickable(false); day7EditText1.setCursorVisible(false);
        day7EditText2.setFocusable(false); day7EditText2.setFocusableInTouchMode(false); day7EditText2.setClickable(false); day7EditText2.setCursorVisible(false);
        day7AccessoryEditText.setFocusable(false); day7AccessoryEditText.setFocusableInTouchMode(false); day7AccessoryEditText.setClickable(false); day7AccessoryEditText.setCursorVisible(false);


    }



    public void fourDaySetup () {

        if (saveButton.getVisibility() == View.VISIBLE) {
            saveButton.setVisibility(View.GONE);
        }

        //Day1
        day1EditText1.setText("Bench");
        day1EditText2.setText("OHP");
        day1AccessoryEditText.setText("Chest, Arms, Back");

        //Day2
        day2EditText1.setText("Squat");
        day2EditText2.setText("Sumo DL");
        day2AccessoryEditText.setText("Legs, Abs");

        //Day3
        day3EditText1.setText("Rest");
        day3EditText2.setText("");
        day3AccessoryEditText.setText("");

        //Day4
        day4EditText1.setText("Bench");
        day4EditText2.setText("C.G. Bench");
        day4AccessoryEditText.setText("Arms, Other");

        //Day5
        day5EditText1.setText("DeadLift");
        day5EditText2.setText("Front Squat");
        day5AccessoryEditText.setText("Back, Abs");

        //Day6
        day6EditText1.setText("Rest");
        day6EditText2.setText("");
        day6AccessoryEditText.setText("");

        //Day7
        day7EditText1.setText("Rest");
        day7EditText2.setText("");
        day7AccessoryEditText.setText("");
    }

    public void fiveDaySetup() {

        if (saveButton.getVisibility() == View.VISIBLE) {
            saveButton.setVisibility(View.GONE);
        }

        //Day1
        day1EditText1.setText("Bench");
        day1EditText2.setText("OHP");
        day1AccessoryEditText.setText("Chest, Arms, Back");

        //Day2
        day2EditText1.setText("Squat");
        day2EditText2.setText("Sumo DL");
        day2AccessoryEditText.setText("Legs, Abs");

        //Day3
        day3EditText1.setText("OHP");
        day3EditText2.setText("Incline Bench");
        day3AccessoryEditText.setText("Shoulders, Chest");

        //Day4
        day4EditText1.setText("DeadLift");
        day4EditText2.setText("Front Squat");
        day4AccessoryEditText.setText("Back, Abs");

        //Day5
        day5EditText1.setText("Bench");
        day5EditText2.setText("C.G. Bench");
        day5AccessoryEditText.setText("Arms, Other");

        //Day6
        day6EditText1.setText("Rest");
        day6EditText2.setText("");
        day6AccessoryEditText.setText("");

        //Day7
        day7EditText1.setText("Rest");
        day7EditText2.setText("");
        day7AccessoryEditText.setText("");
    }

    public void sixDaySquatSetup() {

        if (saveButton.getVisibility() == View.VISIBLE) {
            saveButton.setVisibility(View.GONE);
        }

        //Day1
        day1EditText1.setText("Bench");
        day1EditText2.setText("OHP");
        day1AccessoryEditText.setText("Chest, Arms, Back");

        //Day2
        day2EditText1.setText("Squat");
        day2EditText2.setText("Sumo DL");
        day2AccessoryEditText.setText("Legs, Abs");

        //Day3
        day3EditText1.setText("OHP");
        day3EditText2.setText("Incline Bench");
        day3AccessoryEditText.setText("Shoulders, Chest");

        //Day4
        day4EditText1.setText("DeadLift");
        day4EditText2.setText("Front Squat");
        day4AccessoryEditText.setText("Back, Abs");

        //Day5
        day5EditText1.setText("Bench");
        day5EditText2.setText("C.G. Bench");
        day5AccessoryEditText.setText("Arms, Other");

        //Day6
        day6EditText1.setText("Squat");
        day6EditText2.setText("Sumo DL");
        day6AccessoryEditText.setText("Upper Back, Legs");

        //Day7
        day7EditText1.setText("Rest");
        day7EditText2.setText("");
        day7AccessoryEditText.setText("");
    }

    public void sixDayDeadliftSetup() {

        if (saveButton.getVisibility() == View.VISIBLE) {
            saveButton.setVisibility(View.GONE);
        }

        //Day1
        day1EditText1.setText("Bench");
        day1EditText2.setText("OHP");
        day1AccessoryEditText.setText("Chest, Arms, Back");

        //Day2
        day2EditText1.setText("Squat");
        day2EditText2.setText("Sumo DL");
        day2AccessoryEditText.setText("Legs, Abs");

        //Day3
        day3EditText1.setText("OHP");
        day3EditText2.setText("Incline Bench");
        day3AccessoryEditText.setText("Shoulders, Chest");

        //Day4
        day4EditText1.setText("DeadLift");
        day4EditText2.setText("Front Squat");
        day4AccessoryEditText.setText("Back, Abs");

        //Day5
        day5EditText1.setText("Bench");
        day5EditText2.setText("C.G. Bench");
        day5AccessoryEditText.setText("Arms, Other");

        //Day6
        day6EditText1.setText("DeadLift");
        day6EditText2.setText("Front Squat");
        day6AccessoryEditText.setText("Upper Back, Legs");

        //Day7
        day7EditText1.setText("Rest");
        day7EditText2.setText("");
        day7AccessoryEditText.setText("");
    }

    public void customSetup() {

        saveButton.setVisibility(View.VISIBLE);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("com.jalbers.nsunstest", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String[] storedTexts = new String[21];

        for (int i = 0; i < storedTexts.length; i++) {
            storedTexts[i] = sharedPref.getString("savedText_" + i, "");
        }

        //Day1
        day1EditText1.setText(storedTexts[0]);
        day1EditText2.setText(storedTexts[1]);
        day1AccessoryEditText.setText(storedTexts[2]);

        //Day2
        day2EditText1.setText(storedTexts[3]);
        day2EditText2.setText(storedTexts[4]);
        day2AccessoryEditText.setText(storedTexts[5]);

        //Day3
        day3EditText1.setText(storedTexts[6]);
        day3EditText2.setText(storedTexts[7]);
        day3AccessoryEditText.setText(storedTexts[8]);

        //Day4
        day4EditText1.setText(storedTexts[9]);
        day4EditText2.setText(storedTexts[10]);
        day4AccessoryEditText.setText(storedTexts[11]);

        //Day5
        day5EditText1.setText(storedTexts[12]);
        day5EditText2.setText(storedTexts[13]);
        day5AccessoryEditText.setText(storedTexts[14]);

        //Day6
        day6EditText1.setText(storedTexts[15]);
        day6EditText2.setText(storedTexts[16]);
        day6AccessoryEditText.setText(storedTexts[17]);

        //Day7
        day7EditText1.setText(storedTexts[18]);
        day7EditText2.setText(storedTexts[19]);
        day7AccessoryEditText.setText(storedTexts[20]);
    }

    public void saveEdits (View view) {

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("com.jalbers.nsunstest", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        ArrayList<String> savedEdits = new ArrayList<>();

        loopViews(weekOverviewLayout, savedEdits);


        for (int i = 0; i < savedEdits.size(); i++) {

            editor.putString("savedText_" + i, savedEdits.get(i));
        }
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private void loopViews(ViewGroup view, ArrayList<String> values) {

        for (int i = 0; i < view.getChildCount(); i++) {
            View v = view.getChildAt(i);

            if (v instanceof EditText) {
                // Do something
                values.add(((EditText) v).getText().toString());

            } else if (v instanceof ViewGroup) {

                this.loopViews((ViewGroup) v, values);
            }
        }
    }

    public void uiRefSetup() {

        weekOverviewLayout = (LinearLayout) findViewById(R.id.weekOverviewLayout);

        //Day 1
        day1EditText1 = (EditText) findViewById(R.id.day1EditText1);
        day1EditText2 = (EditText) findViewById(R.id.day1EditText2);
        day1AccessoryEditText = (EditText) findViewById(R.id.day1AccessoryEditText);

        //Day 2
        day2EditText1 = (EditText) findViewById(R.id.day2EditText1);
        day2EditText2 = (EditText) findViewById(R.id.day2EditText2);
        day2AccessoryEditText = (EditText) findViewById(R.id.day2AccessoryEditText);

        //Day 3
        day3EditText1 = (EditText) findViewById(R.id.day3EditText1);
        day3EditText2 = (EditText) findViewById(R.id.day3EditText2);
        day3AccessoryEditText = (EditText) findViewById(R.id.day3AccessoryEditText);

        //Day 4
        day4EditText1 = (EditText) findViewById(R.id.day4EditText1);
        day4EditText2 = (EditText) findViewById(R.id.day4EditText2);
        day4AccessoryEditText = (EditText) findViewById(R.id.day4AccessoryEditText);

        //Day 5
        day5EditText1 = (EditText) findViewById(R.id.day5EditText1);
        day5EditText2 = (EditText) findViewById(R.id.day5EditText2);
        day5AccessoryEditText = (EditText) findViewById(R.id.day5AccessoryEditText);

        //Day 6
        day6EditText1 = (EditText) findViewById(R.id.day6EditText1);
        day6EditText2 = (EditText) findViewById(R.id.day6EditText2);
        day6AccessoryEditText = (EditText) findViewById(R.id.day6AccessoryEditText);

        //Day 7
        day7EditText1 = (EditText) findViewById(R.id.day7EditText1);
        day7EditText2 = (EditText) findViewById(R.id.day7EditText2);
        day7AccessoryEditText = (EditText) findViewById(R.id.day7AccessoryEditText);

        saveButton = (Button) findViewById(R.id.saveButton);


    }



}
