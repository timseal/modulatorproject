// modulator android project
// by me

package com.timseal.modulator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModulatorActivity extends Activity {

    public static final int D = 4;
    private static final String TAG = "Modulator.ModulatorActivity";


    public static final String CHORDS_LIST = "chords_list";
    public static final String NEW_CHORDS_LIST = "new_chords_list";
    public static final String CHANGE_BY = "change_by";
    public static final String SHOW_SHARPS = "show_sharps";
    public static final int B_FLAT = 1;
    public static final int D_FLAT = 4;
    public static final int E_FLAT = 6;
    public static final int G_FLAT = 9;
    public static final int A_FLAT = 11;


    private ChordTransformer chordTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulator);

        ArrayList<Integer> chords = new ArrayList<Integer>(ChordTransformer.MAX_CHORDS);

        if (savedInstanceState != null) {
            chords = savedInstanceState.getIntegerArrayList(CHORDS_LIST);
            Integer changeBy = 0;
            boolean showSharps = true;
            changeBy = savedInstanceState.getInt(CHANGE_BY);
            showSharps = savedInstanceState.getBoolean(SHOW_SHARPS);
            AccidentalType accidentalType;
            if (showSharps) {
                accidentalType = AccidentalType.SHARPS;
            } else {
                accidentalType = AccidentalType.FLATS;
            }
            chordTransformer.setChords(chords);
            chordTransformer.setAccidentalType(accidentalType);

            updateButtonText();
            showChangeByText();
            updateChordDisplay();
            showNewChords();
        } else {
            updateButtonText();
            showChangeByText();
        }

    }


    private void showToast(CharSequence text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(CHORDS_LIST, chordTransformer.getChords());
        outState.putInt(CHANGE_BY, chordTransformer.getChangeBy());
        if (chordTransformer.getAccidentalType() == AccidentalType.SHARPS) {
            outState.putBoolean(SHOW_SHARPS, true);
        } else {
            outState.putBoolean(SHOW_SHARPS, false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void setSharps(View v) {
        chordTransformer.setAccidentalType(AccidentalType.SHARPS);
        updateButtonText();
        updateChordDisplay();
        showNewChords();
    }

    public void setFlats(View v) {
        chordTransformer.setAccidentalType(AccidentalType.FLATS);
        updateButtonText();
        updateChordDisplay();
        showNewChords();
    }

    private void updateButtonText() {
        //yeah this is hideous.
        ((Button) findViewById(R.id.Bb)).setText(chordTransformer.getChordName(B_FLAT));
        ((Button) findViewById(R.id.Db)).setText(chordTransformer.getChordName(D_FLAT));
        ((Button) findViewById(R.id.Eb)).setText(chordTransformer.getChordName(E_FLAT));
        ((Button) findViewById(R.id.Gb)).setText(chordTransformer.getChordName(G_FLAT));
        ((Button) findViewById(R.id.Ab)).setText(chordTransformer.getChordName(A_FLAT));

    }

    private void showChangeByText() {
        TextView changeByText = (TextView) findViewById(R.id.changeByText);
        CharSequence cs = ((Integer) chordTransformer.getChangeBy()).toString();
        Log.d(TAG, "changeBy int: " + chordTransformer.getChangeBy() + ", string: " + cs);
        changeByText.setText(cs);
    }

    public void modulateUp(View v) {
        chordTransformer.modulateUp();

        showChangeByText();
        showNewChords();
    }

    public void modulateDown(View v) {
        chordTransformer.modulateDown();
        showChangeByText();
        showNewChords();
    }







    public void delete(View v) {
        chordTransformer.deleteLastChord();
        updateChordDisplay();
        showNewChords();
    }

    public void clear(View v) {
        chordTransformer.clearAndReset();
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

        chordTransformer.addChord(chordNumber);
        updateChordDisplay();
    }

    private void updateChordDisplay() {
        TextView chordsView = (TextView) findViewById(R.id.chords);
        //chordsView.setText(chordNamesFromNumbers(chords));
        chordsView.setText(chordTransformer.getOriginalChordsText());
    }

    private void showNewChords() {
        TextView newChordsView = (TextView) findViewById(R.id.newchords);
        newChordsView.setText(chordTransformer.getNewChordsText());
    }
}
