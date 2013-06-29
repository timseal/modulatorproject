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
    ArrayList<Integer> newChords = new ArrayList<Integer>(MAX_CHORDS);
    private Integer changeBy = 0;

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
        changeBy += 1;
        newChords.clear();
        recalculate();
        showNewChords();
    }

    public void modulateDown(View v) {
        changeBy -= 1;
        newChords.clear();  //TODO: move this into recalculate()
        recalculate();
        showNewChords();
    }


    private void recalculate() {
        // java's mod for negatives gives negatives
        // workaround is  (a % b + b) % b
        for (Integer chord : chords) {
            Integer newChord = ((chord + changeBy) % 12 + 12) % 12;
            Log.d(TAG, "changeBy = " + changeBy);
            Log.d(TAG, "chord was " + chord + ", changed to " + newChord);
            newChords.add(newChord);
        }
        Log.d(TAG, newChords.toString());
    }


    private CharSequence chordNamesFromNumbers(ArrayList<Integer> chordNumbers) {
        String chordNames = "";
        //loop over all chords
        // change the number into the chord name
        // append the chord name to our output
        for (Integer chordNum : chordNumbers) {
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
            case 0:
                return "A";
            case 1:
                return "Bb";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "C#";
            case 5:
                return "D";
            case 6:
                return "Eb";
            case 7:
                return "E";
            case 8:
                return "F";
            case 9:
                return "F#";
            case 10:
                return "G";
            case 11:
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
        CharSequence chord = "";
        Integer chordNumber = 99;
        switch (v.getId()) {
            case R.id.A:
                chordNumber = 0;
                break;
            case R.id.Bb:
                chordNumber = 1;
                break;
            case R.id.B:
                chordNumber = 2;
                break;
            case R.id.C:
                chordNumber = 3;
                break;
            case R.id.Db:
                chordNumber = 4;
                break;
            case R.id.D:
                chordNumber = 5;
                break;
            case R.id.Eb:
                chordNumber = 6;
                break;
            case R.id.E:
                chordNumber = 7;
                break;
            case R.id.F:
                chordNumber = 8;
                break;
            case R.id.Gb:
                chordNumber = 9;
                break;
            case R.id.G:
                chordNumber = 10;
                break;
            case R.id.Ab:
                chordNumber = 11;
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
        chordsView.setText(chordNamesFromNumbers(chords));
    }

    private void showNewChords() {
        TextView newChordsView = (TextView) findViewById(R.id.newchords);
        newChordsView.setText(chordNamesFromNumbers(newChords));
    }
}
