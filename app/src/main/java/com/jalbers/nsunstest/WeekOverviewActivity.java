package com.jalbers.nsunstest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class WeekOverviewActivity extends AppCompatActivity {

    //UI REF SETUP //

    //SUNDAY
    EditText sunText1;
    EditText sunText2;
    EditText sunText3;

    //MONDAY
    EditText monText1;
    EditText monText2;
    EditText monText3;

    //TUESDAY
    EditText tuesText1;
    EditText tuesText2;
    EditText tuesText3;

    //WEDNESDAY
    EditText wedText1;
    EditText wedText2;
    EditText wedText3;

    //THURSDAY
    EditText thursText1;
    EditText thursText2;
    EditText thursText3;

    //FRIDAY
    EditText friText1;
    EditText friText2;
    EditText friText3;

    //SATURDAY
    EditText satText1;
    EditText satText2;
    EditText satText3;
    ////////////////////////////////////////////////////////////

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_week_overview);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setTitle("Weekly Overview");
        uiRefSetup();

        Intent intent = getIntent();
        counter = intent.getIntExtra("workoutState", -1);

        if (counter == 4) {
            fourDaySetup();
        } else if(counter == 5) {
            fiveDaySetup();
        } else if (counter == 6) {
            sixDaySetup();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        outState.putInt("workoutState", counter);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    /////  MENU CREATION //////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.weekoverview_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.homeMenu:


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("workoutState", counter);
                startActivity(intent);

                return true;

            case R.id.fourDayMenu:

                fourDaySetup();
                counter = 4;
                return true;

            case R.id.fiveDayMenu:

                fiveDaySetup();
                counter = 5;
                return true;

            case R.id.sixDayMenu:

                sixDaySetup();
                counter = 6;
                return true;

            case R.id.customMenu:

                monText1.setText("");
                monText1.setHint("Type here...");
                monText2.setText("");
                monText3.setText("");

                tuesText1.setText("");
                tuesText2.setText("");
                tuesText3.setText("");

                wedText1.setText("");
                wedText2.setText("");
                wedText3.setText("");

                thursText1.setText("");
                thursText2.setText("");
                thursText3.setText("");

                friText1.setText("");
                friText2.setText("");
                friText3.setText("");

                satText1.setText("");
                satText2.setText("");
                satText3.setText("");

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /////////// END MENU CREATION ///////////////////////////////////////



    public void fourDaySetup () {

        monText1.setText("Bench");
        monText2.setText("OHP");
        monText3.setText("Chest, Arms, Back");

        tuesText1.setText("Squat");
        tuesText2.setText("Sumo DL");
        tuesText3.setText("Legs, Abs");

        wedText1.setText("Rest");
        wedText2.setText("");
        wedText3.setText("");

        thursText1.setText("Bench");
        thursText2.setText("C.G. Bench");
        thursText3.setText("Arms, Other");

        friText1.setText("DeadLift");
        friText2.setText("Front Squat");
        friText3.setText("Back, Abs");

        satText1.setText("Rest");
        satText2.setText("");
        satText3.setText("");
    }

    public void fiveDaySetup() {

        monText1.setText("Bench");
        monText2.setText("OHP");
        monText3.setText("Chest, Arms, Back");

        tuesText1.setText("Squat");
        tuesText2.setText("Sumo DL");
        tuesText3.setText("Legs, Abs");

        wedText1.setText("OHP");
        wedText2.setText("Incline Bench");
        wedText3.setText("Shoulders, Chest");

        thursText1.setText("DeadLift");
        thursText2.setText("Front Squat");
        thursText3.setText("Back, Abs");

        friText1.setText("Bench");
        friText2.setText("C.G. Bench");
        friText3.setText("Back, Abs");

        satText1.setText("Rest");
        satText2.setText("");
        satText3.setText("");
    }

    public void sixDaySetup() {

        monText1.setText("Bench");
        monText2.setText("OHP");
        monText3.setText("Chest, Arms, Back");

        tuesText1.setText("Squat");
        tuesText2.setText("Sumo DL");
        tuesText3.setText("Legs, Abs");

        wedText1.setText("OHP");
        wedText2.setText("Incline Bench");
        wedText3.setText("Shoulders, Chest");

        thursText1.setText("DeadLift");
        thursText2.setText("Front Squat");
        thursText3.setText("Back, Abs");

        friText1.setText("Bench");
        friText2.setText("C.G. Bench");
        friText3.setText("Back, Abs");

        satText1.setText("Squat");
        satText2.setText("Sumo DL");
        satText3.setText("Upper Back, Legs");
    }


    public void uiRefSetup () {

        //SUNDAY
        sunText1 = (EditText) findViewById(R.id.sundayText1);
        sunText2 = (EditText) findViewById(R.id.sundayText2);
        sunText3 = (EditText) findViewById(R.id.sundayText3);

        //MONDAY
        monText1 = (EditText) findViewById(R.id.mondayText1);
        monText2 = (EditText) findViewById(R.id.mondayText2);
        monText3 = (EditText) findViewById(R.id.mondayText3);

        //TUESDAY
        tuesText1 = (EditText) findViewById(R.id.tuesdayText1);
        tuesText2 = (EditText) findViewById(R.id.tuesdayText2);
        tuesText3 = (EditText) findViewById(R.id.tuesdayText3);

        //WEDNESDAY
        wedText1 = (EditText) findViewById(R.id.wedText1);
        wedText2 = (EditText) findViewById(R.id.wedText2);
        wedText3 = (EditText) findViewById(R.id.wedText3);

        //THURSDAY
        thursText1 = (EditText) findViewById(R.id.thursText1);
        thursText2 = (EditText) findViewById(R.id.thursText2);
        thursText3 = (EditText) findViewById(R.id.thursText3);

        //FRIDAY
        friText1 = (EditText) findViewById(R.id.friText1);
        friText2 = (EditText) findViewById(R.id.friText2);
        friText3 = (EditText) findViewById(R.id.friText3);

        //SATURDAY
        satText1 = (EditText) findViewById(R.id.satText1);
        satText2 = (EditText) findViewById(R.id.satText2);
        satText3 = (EditText) findViewById(R.id.satText3);
    }
}
