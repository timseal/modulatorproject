// modulator android project
// by me

package com.timseal.modulator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    List<Integer> chords = new ArrayList<Integer>(20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void modulateUp(View v) {
        Toast.makeText(getApplicationContext(), "UP UP AND AWAY", Toast.LENGTH_LONG).show();

        //loop over all chords
        //  +1 to each
        // except 12, which becomes 1.
        // so no mod 12 stuff is even needed!

        // BIG question:
        // will this change the array contents? or will it just throw away the changes?
        for (Integer chord : chords) {
            if (chord >= 1 && chord <= 11) {
                chord += 1;
            } else if (chord == 12) {
                chord = 1;
            } else {
                Log.d("MainActivity", "invalid chord number");
                ///ARGH! error.
            }
        }

    }

    public void modulateDown(View v) {
        Toast.makeText(getApplicationContext(), "THE DOWNWARD SPIRAL", Toast.LENGTH_LONG).show();

        // loop over all chords
        // -1 to each
        // except 1, which becomes 12.

    }

    private CharSequence chordNamesFromNumbers() {
        String chordNames = "";
        //loop over all chords
        // change the number into the chord name
        // append the chord name to our output
        for (Integer chordNum : chords) {
            chordNames = chordNames + getChordName(chordNum);
        }
        return (CharSequence) chordNames;
    }

    private CharSequence getChordName(Integer chordNum) {
        //TODO: boring numbers to chords switch statement thing.
        return "X";
    }

    public void addChord(View v) {
        CharSequence chord = "";
        Integer chordNumber = 0;
        switch (v.getId()) {
            case R.id.A:
                chord = "A";
                chordNumber = 1;
                break;
            case R.id.Bb:
                chord = "Bb";
                chordNumber = 2;
                break;
            case R.id.B:
                chord = "B";
                chordNumber = 3;
                break;
            case R.id.C:
                chord = "C";
                chordNumber = 4;
                break;
            case R.id.Db:
                chord = "Db";
                chordNumber = 5;
                break;
            case R.id.D:
                chord = "D";
                chordNumber = 6;
                break;
            case R.id.Eb:
                chord = "Eb";
                chordNumber = 7;
                break;
            case R.id.E:
                chord = "E";
                chordNumber = 8;
                break;
            case R.id.F:
                chord = "F";
                chordNumber = 9;
                break;
            case R.id.Gb:
                chord = "Gb";
                chordNumber = 10;
                break;
            case R.id.G:
                chord = "G";
                chordNumber = 11;
                break;
            case R.id.Ab:
                chord = "Ab";
                chordNumber = 12;
                break;
            default:
                chord = "??";
        }

        Toast.makeText(getApplicationContext(), chord, Toast.LENGTH_LONG).show();
        updateChordDisplay(chord);
    }

    private void updateChordDisplay(CharSequence chord) {
        TextView chordsView = (TextView) findViewById(R.id.chords);
        chordsView.append(chord + ".");
    }
}
