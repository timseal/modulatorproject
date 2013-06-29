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
    public static final String NEW_CHORDS_LIST = "new_chords_list";
    public static final String CHANGE_BY = "change_by";
    public static final String SHOW_SHARPS = "show_sharps";

    ArrayList<Integer> chords = new ArrayList<Integer>(MAX_CHORDS);
    ArrayList<Integer> newChords = new ArrayList<Integer>(MAX_CHORDS);
    private int changeBy = 0;

    private boolean showSharps = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            chords = savedInstanceState.getIntegerArrayList(CHORDS_LIST);
            newChords = savedInstanceState.getIntegerArrayList(NEW_CHORDS_LIST);
            changeBy = savedInstanceState.getInt(CHANGE_BY);
            showSharps = savedInstanceState.getBoolean(SHOW_SHARPS);
            updateChordDisplay();
            showNewChords();
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(CHORDS_LIST, chords);
        outState.putIntegerArrayList(NEW_CHORDS_LIST, newChords);
        outState.putInt(CHANGE_BY, changeBy);
        outState.putBoolean(SHOW_SHARPS, showSharps);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void setSharps(View v) {
        showSharps = true;
        updateChordDisplay();
        showNewChords();
    }

    public void setFlats(View v) {
        showSharps = false;
        updateChordDisplay();
        showNewChords();
    }

    public void modulateUp(View v) {
        changeBy += 1;
        recalculate();
        showNewChords();
    }

    public void modulateDown(View v) {
        changeBy -= 1;
        recalculate();
        showNewChords();
    }


    private void recalculate() {
        newChords.clear();
        // java's mod for negatives gives negatives, which is crap.
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

        // TODO: have two arrays of twelve chord names, one for sharps, one for flats.
        // then return sharps(chordNum) or flats(chordNum)
        // in fact this method probably vanishes if I do that.
        if (showSharps) {
            switch (chordNum) {
                case 0:
                    return "A";
                case 1:
                    return "A#";
                case 2:
                    return "B";
                case 3:
                    return "C";
                case 4:
                    return "C#";
                case 5:
                    return "D";
                case 6:
                    return "D#";
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
        } else { // showing flats
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
                    return "Db";
                case 5:
                    return "D";
                case 6:
                    return "Eb";
                case 7:
                    return "E";
                case 8:
                    return "F";
                case 9:
                    return "Gb";
                case 10:
                    return "G";
                case 11:
                    return "Ab";
                default:
                    return "error";

            }
        }
    }


    public void delete(View v) {
        int size = chords.size();
        if (size > 0) {
            chords.remove(size - 1);
            recalculate();
            updateChordDisplay();
            showNewChords();
        }
        // if size is zero, then do nothing.
    }

    public void clear(View v) {
        chords.clear();
        newChords.clear();
        updateChordDisplay();
        showNewChords();
    }

    public void addChord(View v) {
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
                chordNumber = 99;  // very ZX-BASIC of me, I know
        }

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
