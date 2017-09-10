package com.jalbers.nsunstest;

import android.animation.Animator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.R.attr.animation;

public class MainActivity extends AppCompatActivity {


    int selectedLift = 1;
    int workoutState;
    boolean fiveThreeOne = true;
    boolean secondLift = false;
    boolean offDay = false;



    //// UI REF SETUP ///////////////////////////

    ConstraintLayout mainLayout;
    HorizontalScrollView horizontalScrollView;
    LinearLayout bigFourLinearLayout;
    RelativeLayout maxLayout;
    ScrollView verticalScrollView;
    LinearLayout workoutLinearLayout;
    LinearLayout bottomButtonsLayout;

    EditText squatEditText;
    EditText benchEditText;
    EditText deadliftEditText;
    EditText overheadpressEditText;

    TextView secondLiftTextView;
    TextView fiveThreeOneTextView;
    TextView offDayTextView;

    TextView weekTextView;
    TextView maxTextView;
    TextView howToTextView;
    ImageView settingsImageView;

    TextView set1TextView;
    TextView set1NumTextView;

    TextView set2TextView;
    TextView set2NumTextView;

    TextView set3TextView;
    TextView set3NumTextView;

    TextView set4TextView;
    TextView set4NumTextView;

    TextView set5TextView;
    TextView set5NumTextView;

    TextView set6TextView;
    TextView set6NumTextView;

    TextView set7TextView;
    TextView set7NumTextView;

    TextView set8TextView;
    TextView set8NumTextView;

    TextView set9TextView;
    TextView set9NumTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        uiRefSetup();

        Intent intent = getIntent();
        workoutState = intent.getIntExtra("workoutState", -1);

        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);

        squatEditText.setOnKeyListener(onKeyListener);
        benchEditText.setOnKeyListener(onKeyListener);
        deadliftEditText.setOnKeyListener(onKeyListener);
        overheadpressEditText.setOnKeyListener(onKeyListener);


        activeLift(selectedLift);
        setFiveThreeOneText();

        ///// SETS THE "CENTER SNAPPING" MOTION OF THE TOP HORIZONTAL SCROLL ///////////////
        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {

                    int center = horizontalScrollView.getScrollX() + horizontalScrollView.getWidth()/2;
                    int childNum = bigFourLinearLayout.getChildCount();

                    for (int i = 0; i < childNum; i++) {

                        View view1 = bigFourLinearLayout.getChildAt(i);
                        int viewLeft = view1.getLeft();
                        int viewWidth = view1.getWidth();

                        if (center >= viewLeft && center <= viewLeft + viewWidth) {

                            horizontalScrollView.scrollBy((viewLeft + viewWidth /2 ) - center, 0);
                            selectedLift = i;
                            activeLift(selectedLift);
                            if (fiveThreeOne) setFiveThreeOneText();
                            if (secondLift) setSecondLiftText();
                            if (offDay) setOffDayText();

                            break;
                        }
                    }
                }
                return false;
            }
        });////////////////////////////////////////////////////////////////////////////////////

    }

    ////// SETTING NUMBERS FOR ACTUAL WORKOUT ///////////////////////

    public double round5(double n) {

        double temp = n%5;
        if (temp<2.5)
            return n-temp;
        else
            return n+5-temp;
    }


    public void activeLift(int n) {

        if (n == 1) {
            //SQUAT
            secondLiftTextView.setText("Sumo DL");
        } else if (n == 2) {
            //BENCH
            secondLiftTextView.setText("C.G. Bench");
        } else if (n == 3) {
            //DEADLIFT
            secondLiftTextView.setText("Front Squat");
        }else if (n == 4) {
            //OHP
            secondLiftTextView.setText("Incline Bench");
        }
    }

    public void setFiveThreeOne (View view) {

        fiveThreeOne = true;
        secondLift = false;
        offDay = false;

        fiveThreeOneTextView.setTextColor(Color.WHITE);
        secondLiftTextView.setTextColor(Color.BLACK);
        offDayTextView.setTextColor(Color.BLACK);

        LinearLayout set9LinearLayout = (LinearLayout) findViewById(R.id.set9LinearLayout);
        set9LinearLayout.setVisibility(View.VISIBLE);

        setFiveThreeOneText();

    }
    //// SETS TEXT/WEIGHTS FOR THE MAIN 5/3/1 LIFT WHEN CLICKED OR DRAGGED //////////////
    public void setFiveThreeOneText () {

        double weight;

        if (selectedLift == 1) {
            //SQUAT
            weight = Double.parseDouble(squatEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .75) + " x 5");
            set2NumTextView.setText((int) round5(weight * .85) + " x 3");
            set3NumTextView.setText((int) round5(weight * .95) + " x 1+");
            set4NumTextView.setText((int) round5(weight * .90) + " x 3");
            set5NumTextView.setText((int) round5(weight * .85) + " x 3");
            set6NumTextView.setText((int) round5(weight * .80) + " x 3");
            set7NumTextView.setText((int) round5(weight * .75) + " x 5");
            set8NumTextView.setText((int) round5(weight * .70) + " x 5");
            set9NumTextView.setText((int) round5(weight * .65) + " x 5+");

            set1TextView.setText("Set 1 - 75%");
            set2TextView.setText("Set 2 - 85%");
            set3TextView.setText("Set 3 - 95%");
            set4TextView.setText("Set 4 - 90%");
            set5TextView.setText("Set 5 - 85%");
            set6TextView.setText("Set 6 - 80%");
            set7TextView.setText("Set 7 - 75%");
            set8TextView.setText("Set 8 - 70%");
            set9TextView.setText("Set 9 - 65%");

        } else if (selectedLift == 2) {
            //BENCH
            weight = Double.parseDouble(benchEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .75) + " x 5");
            set2NumTextView.setText((int) round5(weight * .85) + " x 3");
            set3NumTextView.setText((int) round5(weight * .95) + " x 1+");
            set4NumTextView.setText((int) round5(weight * .90) + " x 3");
            set5NumTextView.setText((int) round5(weight * .85) + " x 5");
            set6NumTextView.setText((int) round5(weight * .80) + " x 3");
            set7NumTextView.setText((int) round5(weight * .75) + " x 5");
            set8NumTextView.setText((int) round5(weight * .70) + " x 3");
            set9NumTextView.setText((int) round5(weight * .65) + " x 5+");

            set1TextView.setText("Set 1 - 75%");
            set2TextView.setText("Set 2 - 85%");
            set3TextView.setText("Set 3 - 95%");
            set4TextView.setText("Set 4 - 90%");
            set5TextView.setText("Set 5 - 85%");
            set6TextView.setText("Set 6 - 80%");
            set7TextView.setText("Set 7 - 75%");
            set8TextView.setText("Set 8 - 70%");
            set9TextView.setText("Set 9 - 65%");

        } else if (selectedLift == 3) {
            //DEAD LIFT
            weight = Double.parseDouble(deadliftEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .75) + " x 5");
            set2NumTextView.setText((int) round5(weight * .85) + " x 3");
            set3NumTextView.setText((int) round5(weight * .95) + " x 1+");
            set4NumTextView.setText((int) round5(weight * .90) + " x 3");
            set5NumTextView.setText((int) round5(weight * .85) + " x 3");
            set6NumTextView.setText((int) round5(weight * .80) + " x 3");
            set7NumTextView.setText((int) round5(weight * .75) + " x 3");
            set8NumTextView.setText((int) round5(weight * .70) + " x 3");
            set9NumTextView.setText((int) round5(weight * .65) + " x 3+");

            set1TextView.setText("Set 1 - 75%");
            set2TextView.setText("Set 2 - 85%");
            set3TextView.setText("Set 3 - 95%");
            set4TextView.setText("Set 4 - 90%");
            set5TextView.setText("Set 5 - 85%");
            set6TextView.setText("Set 6 - 80%");
            set7TextView.setText("Set 7 - 75%");
            set8TextView.setText("Set 8 - 70%");
            set9TextView.setText("Set 9 - 65%");

        } else if (selectedLift == 4) {
            //OHP
            weight = Double.parseDouble(overheadpressEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .75) + " x 5");
            set2NumTextView.setText((int) round5(weight * .85) + " x 3");
            set3NumTextView.setText((int) round5(weight * .95) + " x 1+");
            set4NumTextView.setText((int) round5(weight * .90) + " x 3");
            set5NumTextView.setText((int) round5(weight * .85) + " x 3");
            set6NumTextView.setText((int) round5(weight * .80) + " x 3");
            set7NumTextView.setText((int) round5(weight * .75) + " x 5");
            set8NumTextView.setText((int) round5(weight * .70) + " x 5");
            set9NumTextView.setText((int) round5(weight * .65) + " x 5+");

            set1TextView.setText("Set 1 - 75%");
            set2TextView.setText("Set 2 - 85%");
            set3TextView.setText("Set 3 - 95%");
            set4TextView.setText("Set 4 - 90%");
            set5TextView.setText("Set 5 - 85%");
            set6TextView.setText("Set 6 - 80%");
            set7TextView.setText("Set 7 - 75%");
            set8TextView.setText("Set 8 - 70%");
            set9TextView.setText("Set 9 - 65%");
        }

    } /////////////////////////////////////////////////////////////////

    //// SETS TEXT/WEIGHTS FOR THE SECONDARY LIFT WHEN CLICKED OR DRAGGED //////////////
    public void setSecondLift (View view) {

        secondLift = true;
        fiveThreeOne = false;
        offDay = false;

        secondLiftTextView.setTextColor(Color.WHITE);
        fiveThreeOneTextView.setTextColor(Color.BLACK);
        offDayTextView.setTextColor(Color.BLACK);

        LinearLayout set9LinearLayout = (LinearLayout) findViewById(R.id.set9LinearLayout);
        set9LinearLayout.setVisibility(View.GONE);

        setSecondLiftText();
    }

    public void setSecondLiftText () {

        double weight;

        if (selectedLift == 1) {
            //SUMO DL
            weight = Double.parseDouble(deadliftEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .5) + " x 5");
            set2NumTextView.setText((int) round5(weight * .6) + " x 5");
            set3NumTextView.setText((int) round5(weight * .7) + " x 3");
            set4NumTextView.setText((int) round5(weight * .7) + " x 5");
            set5NumTextView.setText((int) round5(weight * .7) + " x 7");
            set6NumTextView.setText((int) round5(weight * .7) + " x 4");
            set7NumTextView.setText((int) round5(weight * .7) + " x 6");
            set8NumTextView.setText((int) round5(weight * .7) + " x 8");

            set1TextView.setText("Set 1 - 50%");
            set2TextView.setText("Set 2 - 60%");
            set3TextView.setText("Set 3 - 70%");
            set4TextView.setText("Set 4 - 70%");
            set5TextView.setText("Set 5 - 70%");
            set6TextView.setText("Set 6 - 70%");
            set7TextView.setText("Set 7 - 70%");
            set8TextView.setText("Set 8 - 70%");

        } else if (selectedLift == 2) {
            //CG BENCH
            weight = Double.parseDouble(benchEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .4) + " x 5");
            set2NumTextView.setText((int) round5(weight * .5) + " x 5");
            set3NumTextView.setText((int) round5(weight * .6) + " x 3");
            set4NumTextView.setText((int) round5(weight * .6) + " x 5");
            set5NumTextView.setText((int) round5(weight * .6) + " x 7");
            set6NumTextView.setText((int) round5(weight * .6) + " x 4");
            set7NumTextView.setText((int) round5(weight * .6) + " x 6");
            set8NumTextView.setText((int) round5(weight * .6) + " x 8");

            set1TextView.setText("Set 1 - 40%");
            set2TextView.setText("Set 2 - 50%");
            set3TextView.setText("Set 3 - 60%");
            set4TextView.setText("Set 4 - 60%");
            set5TextView.setText("Set 5 - 60%");
            set6TextView.setText("Set 6 - 60%");
            set7TextView.setText("Set 7 - 60%");
            set8TextView.setText("Set 8 - 60%");

        } else if (selectedLift == 3) {
            //FRONT SQUAT
            weight = Double.parseDouble(squatEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .35) + " x 5");
            set2NumTextView.setText((int) round5(weight * .45) + " x 5");
            set3NumTextView.setText((int) round5(weight * .55) + " x 3");
            set4NumTextView.setText((int) round5(weight * .55) + " x 5");
            set5NumTextView.setText((int) round5(weight * .55) + " x 7");
            set6NumTextView.setText((int) round5(weight * .55) + " x 4");
            set7NumTextView.setText((int) round5(weight * .55) + " x 6");
            set8NumTextView.setText((int) round5(weight * .55) + " x 8");

            set1TextView.setText("Set 1 - 35%");
            set2TextView.setText("Set 2 - 45%");
            set3TextView.setText("Set 3 - 55%");
            set4TextView.setText("Set 4 - 55%");
            set5TextView.setText("Set 5 - 55%");
            set6TextView.setText("Set 6 - 55%");
            set7TextView.setText("Set 7 - 55%");
            set8TextView.setText("Set 8 - 55%");

        } else if (selectedLift == 4) {
            //INCLINE BENCH
            weight = Double.parseDouble(benchEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .4) + " x 5");
            set2NumTextView.setText((int) round5(weight * .5) + " x 5");
            set3NumTextView.setText((int) round5(weight * .6) + " x 3");
            set4NumTextView.setText((int) round5(weight * .6) + " x 5");
            set5NumTextView.setText((int) round5(weight * .6) + " x 7");
            set6NumTextView.setText((int) round5(weight * .6) + " x 4");
            set7NumTextView.setText((int) round5(weight * .6) + " x 6");
            set8NumTextView.setText((int) round5(weight * .6) + " x 8");

            set1TextView.setText("Set 1 - 40%");
            set2TextView.setText("Set 2 - 50%");
            set3TextView.setText("Set 3 - 60%");
            set4TextView.setText("Set 4 - 60%");
            set5TextView.setText("Set 5 - 60%");
            set6TextView.setText("Set 6 - 60%");
            set7TextView.setText("Set 7 - 60%");
            set8TextView.setText("Set 8 - 60%");
        }
    }

    //// SETS TEXT/WEIGHTS FOR THE OFF DAY WHEN CLICKED OR DRAGGED //////////////
    public void setOffDay(View view) {

        fiveThreeOne = false;
        secondLift = false;
        offDay = true;

        fiveThreeOneTextView.setTextColor(Color.BLACK);
        secondLiftTextView.setTextColor(Color.BLACK);
        offDayTextView.setTextColor(Color.WHITE);

        LinearLayout set9LinearLayout = (LinearLayout) findViewById(R.id.set9LinearLayout);
        set9LinearLayout.setVisibility(View.VISIBLE);

        setOffDayText();

    }

    public void setOffDayText() {

        double weight;

        if (selectedLift == 1) {
            //SQUAT
            weight = Double.parseDouble(squatEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .725) + " x 3");
            set2NumTextView.setText((int) round5(weight * .725) + " x 3");
            set3NumTextView.setText((int) round5(weight * .725) + " x 3");
            set4NumTextView.setText((int) round5(weight * .725) + " x 3");
            set5NumTextView.setText((int) round5(weight * .725) + " x 3");
            set6NumTextView.setText((int) round5(weight * .725) + " x 3");
            set7NumTextView.setText((int) round5(weight * .725) + " x 3");
            set8NumTextView.setText((int) round5(weight * .725) + " x 3");
            set9NumTextView.setText((int) round5(weight * .725) + " x 3+");

            set1TextView.setText("Set 1 - 72.5%");
            set2TextView.setText("Set 2 - 72.5%");
            set3TextView.setText("Set 3 - 72.5%");
            set4TextView.setText("Set 4 - 72.5%");
            set5TextView.setText("Set 5 - 72.5%");
            set6TextView.setText("Set 6 - 72.5%");
            set7TextView.setText("Set 7 - 72.5%");
            set8TextView.setText("Set 8 - 72.5%");
            set9TextView.setText("Set 9 - 72.5%");

        } else if (selectedLift == 2) {
            //BENCH
            weight = Double.parseDouble(benchEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .65) + " x 8");
            set2NumTextView.setText((int) round5(weight * .75) + " x 6");
            set3NumTextView.setText((int) round5(weight * .85) + " x 4");
            set4NumTextView.setText((int) round5(weight * .85) + " x 4");
            set5NumTextView.setText((int) round5(weight * .85) + " x 4");
            set6NumTextView.setText((int) round5(weight * .80) + " x 5");
            set7NumTextView.setText((int) round5(weight * .75) + " x 6");
            set8NumTextView.setText((int) round5(weight * .70) + " x 7");
            set9NumTextView.setText((int) round5(weight * .65) + " x 8+");

            set1TextView.setText("Set 1 - 65%");
            set2TextView.setText("Set 2 - 75%");
            set3TextView.setText("Set 3 - 85%");
            set4TextView.setText("Set 4 - 85%");
            set5TextView.setText("Set 5 - 85%");
            set6TextView.setText("Set 6 - 80%");
            set7TextView.setText("Set 7 - 75%");
            set8TextView.setText("Set 8 - 70%");
            set9TextView.setText("Set 9 - 65%");

        } else if (selectedLift == 3) {
            //DEAD LIFT
            weight = Double.parseDouble(deadliftEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .725) + " x 3");
            set2NumTextView.setText((int) round5(weight * .725) + " x 3");
            set3NumTextView.setText((int) round5(weight * .725) + " x 3");
            set4NumTextView.setText((int) round5(weight * .725) + " x 3");
            set5NumTextView.setText((int) round5(weight * .725) + " x 3");
            set6NumTextView.setText((int) round5(weight * .725) + " x 3");
            set7NumTextView.setText((int) round5(weight * .725) + " x 3");
            set8NumTextView.setText((int) round5(weight * .725) + " x 3");
            set9NumTextView.setText((int) round5(weight * .725) + " x 3+");

            set1TextView.setText("Set 1 - 72.5%");
            set2TextView.setText("Set 2 - 72.5%");
            set3TextView.setText("Set 3 - 72.5%");
            set4TextView.setText("Set 4 - 72.5%");
            set5TextView.setText("Set 5 - 72.5%");
            set6TextView.setText("Set 6 - 72.5%");
            set7TextView.setText("Set 7 - 72.5%");
            set8TextView.setText("Set 8 - 72.5%");
            set9TextView.setText("Set 9 - 72.5%");

        } else if (selectedLift == 4) {
            //OHP
            weight = Double.parseDouble(overheadpressEditText.getText().toString());

            set1NumTextView.setText((int) round5(weight * .65) + " x 8");
            set2NumTextView.setText((int) round5(weight * .75) + " x 6");
            set3NumTextView.setText((int) round5(weight * .85) + " x 4");
            set4NumTextView.setText((int) round5(weight * .85) + " x 4");
            set5NumTextView.setText((int) round5(weight * .85) + " x 4");
            set6NumTextView.setText((int) round5(weight * .80) + " x 5");
            set7NumTextView.setText((int) round5(weight * .75) + " x 6");
            set8NumTextView.setText((int) round5(weight * .70) + " x 7");
            set9NumTextView.setText((int) round5(weight * .65) + " x 8+");

            set1TextView.setText("Set 1 - 65%");
            set2TextView.setText("Set 2 - 75%");
            set3TextView.setText("Set 3 - 85%");
            set4TextView.setText("Set 4 - 85%");
            set5TextView.setText("Set 5 - 85%");
            set6TextView.setText("Set 6 - 80%");
            set7TextView.setText("Set 7 - 75%");
            set8TextView.setText("Set 8 - 70%");
            set9TextView.setText("Set 9 - 65%");
        }

    } ////////////////////////////////////////////////////////////////////
    //////////////// END SETTING WORKOUT TEXTS/NUMBERS ////////////////////


    //// CODE FOR EDITING MAIN LIFT TEXTS AT TOP OF FIELD ////////////////////////
    View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            // If the event is a key-down event on the "enter" button
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Perform action on key press

                try {
                    if (fiveThreeOne) {
                        setFiveThreeOneText();
                    } else if (secondLift) {
                        setSecondLiftText();
                    } else if (offDay) {
                        setOffDayText();
                    }
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    return true;

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Please insert a number", Toast.LENGTH_SHORT).show();
                }

            }
            return false;
        }
    }; ////////////////////////////////////////////////////////////////////////////////////






    //////////  ON CLICK METHODS FOR BOTTOM SCREEN BUTTONS/LAYOUT /////////////////////////
    public void toWeekOverview (View view) {

        Intent intent = new Intent(getApplicationContext(), WeekOverviewActivity.class);
        intent.putExtra("workoutState", workoutState);
        startActivity(intent);
    }

    public void toSettings (View view) {

        //Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        //startActivity(intent);
    }

    ////// MAX BUTTON CODE ///////////////////////////////////
    public void toSetMax (View view) {

        final EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
        final EditText repsEditText = (EditText) findViewById(R.id.repsEditText);
        final TextView calculatedMaxTextView = (TextView) findViewById(R.id.calculatedMaxTextView);
        final CheckBox trainingMaxCheckBox = (CheckBox) findViewById(R.id.trainingMaxCheckBox);

        maxLayout.animate().translationY(-75)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        maxLayout.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {}

                    @Override
                    public void onAnimationCancel(Animator animator) {}

                    @Override
                    public void onAnimationRepeat(Animator animator) {}
                });

        horizontalScrollView.setAlpha((float) .25);
        verticalScrollView.setAlpha((float) .25);
        workoutLinearLayout.setAlpha((float) .25);
        bottomButtonsLayout.setAlpha((float) .25);

        View.OnKeyListener keyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                // If the event is a key-down event on the "enter" button
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)) {

                    try {
                        double weight = Double.parseDouble(weightEditText.getText().toString());
                        double reps = Double.parseDouble(repsEditText.getText().toString());
                        double result = round5(weight * reps * (.0333) + weight);

                        if (trainingMaxCheckBox.isChecked()){
                            result = round5(result *.9);
                        }

                        calculatedMaxTextView.setText(String.valueOf(result));

                        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        return true;

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Please insert a number", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        };
        weightEditText.setOnKeyListener(keyListener);
        repsEditText.setOnKeyListener(keyListener);
    }

    public void closeMax (View view) {
        // THIS IS THE X BUTTON ///
        maxLayout.animate().translationY(1500)
                .setListener(new Animator.AnimatorListener() {
                                 @Override
                                 public void onAnimationStart(Animator animator) {}
                                 @Override
                                 public void onAnimationEnd(Animator animator) {

                                     maxLayout.setVisibility(View.GONE);
                                 }
                                 @Override
                                 public void onAnimationCancel(Animator animator) {}
                                 @Override
                                 public void onAnimationRepeat(Animator animator) {}
                });

        horizontalScrollView.setAlpha((float) 1);
        verticalScrollView.setAlpha((float) 1);
        workoutLinearLayout.setAlpha((float) 1);
        bottomButtonsLayout.setAlpha((float) 1);
    }


    ////////////////////////////////////ALL THE UI FINDVIEWBYID STUFF/////////////////////////////////////////////////////
    public void uiRefSetup () {

        mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        bigFourLinearLayout = (LinearLayout) findViewById(R.id.bigFourLayout);
        maxLayout = (RelativeLayout) findViewById(R.id.maxLayout);
        verticalScrollView = (ScrollView) findViewById(R.id.verticalScrollView);
        workoutLinearLayout = (LinearLayout) findViewById(R.id.workoutLinearLayout);
        bottomButtonsLayout = (LinearLayout) findViewById(R.id.bottomButtonsLayout);

        squatEditText = (EditText) findViewById(R.id.squatEditText);
        benchEditText = (EditText) findViewById(R.id.benchEditText);
        deadliftEditText = (EditText) findViewById(R.id.deadliftEditText);
        overheadpressEditText = (EditText) findViewById(R.id.overheadpressEditText);

        secondLiftTextView = (TextView) findViewById(R.id.secondLiftTextView);
        fiveThreeOneTextView = (TextView) findViewById(R.id.fiveThreeOneTextView);
        offDayTextView = (TextView) findViewById(R.id.offdayTextView);

        weekTextView = (TextView) findViewById(R.id.weekTextView);
        maxTextView = (TextView) findViewById(R.id.maxTextView);
        howToTextView = (TextView) findViewById(R.id.howToTextView);
        settingsImageView = (ImageView) findViewById(R.id.settingsImageView);

        set1TextView = (TextView) findViewById(R.id.set1TextView);
        set1NumTextView = (TextView) findViewById(R.id.set1NumTextView);

        set2TextView = (TextView) findViewById(R.id.set2TextView);
        set2NumTextView = (TextView) findViewById(R.id.set2NumTextView);

        set3TextView = (TextView) findViewById(R.id.set3TextView);
        set3NumTextView = (TextView) findViewById(R.id.set3NumTextView);

        set4TextView = (TextView) findViewById(R.id.set4TextView);
        set4NumTextView = (TextView) findViewById(R.id.set4NumTextView);

        set5TextView = (TextView) findViewById(R.id.set5TextView);
        set5NumTextView = (TextView) findViewById(R.id.set5NumTextView);

        set6TextView = (TextView) findViewById(R.id.set6TextView);
        set6NumTextView = (TextView) findViewById(R.id.set6NumTextView);

        set7TextView = (TextView) findViewById(R.id.set7TextView);
        set7NumTextView = (TextView) findViewById(R.id.set7NumTextView);

        set8TextView = (TextView) findViewById(R.id.set8TextView);
        set8NumTextView = (TextView) findViewById(R.id.set8NumTextView);

        set9TextView = (TextView) findViewById(R.id.set9TextView);
        set9NumTextView = (TextView) findViewById(R.id.set9NumTextView);
    }
}
