package com.example.cemo.diceout;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //UI Components
    TextView rollResult;
    TextView rollScore;
    Button rollButton;

    //Results of rolling die
    int die1;
    int die2;
    int die3;

    Random rand;

    //Arraylist that store results
    ArrayList<Integer> dice;
    ArrayList<ImageView> diceImageViews;

    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rand = new Random();

        //Text views that
        rollResult = (TextView) findViewById(R.id.rollResult);
        rollScore = (TextView) findViewById(R.id.rollScore);
        rollButton = (Button) findViewById(R.id.rollButton);

        dice = new ArrayList<Integer>();
        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);
    }

    public void rollDice (View v ) {

        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;
        String score = "Score of roll: " + (die1 + die2 + die3);
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int i = 0; i < 3; i++) {
            String imageName = "die_" + dice.get(i) + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(i).setImageDrawable(d);
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

        String r = "Die 1:" + die1 + "\nDie 2:" + die2 + "\nDie 3:" + die3;
        //Setting text to the the screen
        rollResult.setText(r);
        rollScore.setText(score);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
