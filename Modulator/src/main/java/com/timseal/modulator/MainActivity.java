// modulator android project
// by me

package com.timseal.modulator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = "Modulator.MainActivity";
    public static final int MAX_CHORDS = 20;
    public static final String CHORDS_LIST = "chords_list";
    ArrayList<Integer> chords = new ArrayList<Integer>(MAX_CHORDS);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(savedInstanceState != null) {
//            chords = savedInstanceState.getIntegerArrayList(CHORDS_LIST);
//        }
        //TODO: keep state on orientation changes!!
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void modulateUp(View v) {

        //loop over all chords
        //  +1 to each
        // except 12, which becomes 1.
        // so no mod 12 stuff is even needed!

        ArrayList<Integer> newChords = new ArrayList<Integer>(MAX_CHORDS);

        for (Integer chord : chords) {
            Integer newChord;
            if (chord >= 1 && chord <= 11) {
                newChord = chord + 1;
            } else if (chord == 12) {
                newChord = 1;
            } else {
                Log.d(TAG, "invalid chord number");
                newChord = -1;
                ///ARGH! error.
            }
            Log.d(TAG, "chord was " + chord + ", changed to " + newChord);
            newChords.add(newChord);
        }
        Log.d(TAG, newChords.toString());
        chords = newChords; //possible sync bug
        //Toast.makeText(getApplicationContext(), chordNamesFromNumbers(), Toast.LENGTH_LONG).show();
        showNewChords();
    }


    public void modulateDown(View v) {
        // loop over all chords
        // -1 to each
        // except 1, which becomes 12.
        ArrayList<Integer> newChords = new ArrayList<Integer>(MAX_CHORDS);

        for (Integer chord : chords) {
            Integer newChord;
            if (chord >= 2 && chord <= 12) {
                newChord = chord - 1;
            } else if (chord == 1) {
                newChord = 12;
            } else {
                Log.d(TAG, "invalid chord number");
                newChord = -1;
                ///ARGH! error.
            }
            Log.d(TAG, "chord was " + chord + ", changed to " + newChord);
            newChords.add(newChord);
        }
        Log.d(TAG, newChords.toString());
        chords = newChords; //possible sync bug
        //Toast.makeText(getApplicationContext(), chordNamesFromNumbers(), Toast.LENGTH_LONG).show();
        showNewChords();
    }


    private CharSequence chordNamesFromNumbers() {
        String chordNames = "";
        //loop over all chords
        // change the number into the chord name
        // append the chord name to our output
        for (Integer chordNum : chords) {
            chordNames = chordNames + getChordName(chordNum) + " ";
        }
        return (CharSequence) chordNames;
    }

    private CharSequence getChordName(Integer chordNum) {
        //No breaks because they are return statements.

        // When showing a sharp/flat, I have preferences.
        // they are F#, G#, Bb, C#, Eb.
        //Probably because I mostly play in certain keys - or I might be just strange.
        switch (chordNum) {
            case 1:
                return "A";
            case 2:
                return "Bb";
            case 3:
                return "B";
            case 4:
                return "C";
            case 5:
                return "C#";
            case 6:
                return "D";
            case 7:
                return "Eb";
            case 8:
                return "E";
            case 9:
                return "F";
            case 10:
                return "F#";
            case 11:
                return "G";
            case 12:
                return "G#";
            default:
                return "error";
        }
    }


    public void delete(View v) {
//        Toast.makeText(getApplicationContext(), "delete not done yet", Toast.LENGTH_LONG).show();
        int size = chords.size();
        if (size > 0) {
            chords.remove(size - 1);
            updateChordDisplay();
            showNewChords();
        }
        // if size is zero, then do nothing.
    }

    public void clear(View v) {
//        Toast.makeText(getApplicationContext(), "clear not done yet", Toast.LENGTH_LONG).show();
        chords.clear();
        updateChordDisplay();
        showNewChords();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putIntegerArrayList(CHORDS_LIST, chords);
//    }

    public void addChord(View v) {
        //TODO: remove chord character, the translation happens elsewhere now
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

        //Toast.makeText(getApplicationContext(), chord, Toast.LENGTH_LONG).show();
        chords.add(chordNumber);
        updateChordDisplay();
    }

    private void updateChordDisplay() {
        TextView chordsView = (TextView) findViewById(R.id.chords);
        chordsView.setText(chordNamesFromNumbers());
    }

    private void showNewChords() {
        TextView newChordsView = (TextView) findViewById(R.id.newchords);
        newChordsView.setText(chordNamesFromNumbers());
    }
}
