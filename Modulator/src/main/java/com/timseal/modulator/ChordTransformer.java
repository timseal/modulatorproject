package com.timseal.modulator;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by tim on 7/4/13.
 */
public class ChordTransformer {
    public static final int MAX_CHORDS = 20;
    private static final String TAG = "ChordTransformer";
    private ArrayList<Integer> _chords = new ArrayList<Integer>(20);
    private ArrayList<Integer> _newChords = new ArrayList<Integer>(20);
    private AccidentalType _accidentalType = AccidentalType.SHARPS;
    private Integer _changeBy = 0;

    public Integer getChangeBy() {
        return _changeBy;
    }

    public void setChangeBy(Integer changeBy) {
        this._changeBy = changeBy;
    }

    public ArrayList<Integer> getChords() {
        return _chords;
    }

    public void setChords(ArrayList<Integer> chords) {
        this._chords = chords;
    }

    public AccidentalType getAccidentalType() {
        return _accidentalType;
    }

    public void setAccidentalType(AccidentalType accidentalType) {
        this._accidentalType = accidentalType;
    }

    public CharSequence getOriginalChordsText() {
        return "in a minute";
    }

    public CharSequence getNewChordsText() {
        return "patience";
    }


    public ChordTransformer(ArrayList<Integer> chords, AccidentalType accidentalType, Integer changeBy) {
        setChords(chords);
        setAccidentalType(accidentalType);
        setChangeBy(changeBy);
    }


    public void modulateUp() {
        _changeBy += 1;
    }

    public void modulateDown() {
        _changeBy -= 1;
    }

    public void addChord(Integer chordNumber) {
        _chords.add(chordNumber);
    }

    public void clearAndReset() {
        _chords.clear();
        _changeBy = 0;
    }

    public void deleteLastChord() {
        int size = _chords.size();
        if (size > 0) {
            _chords.remove(size - 1);
        }
    }

    private void recalculate() {
        _newChords.clear();
        // java's mod for negatives gives negatives, which is crap.
        // workaround is  (a % b + b) % b
        for (Integer chord : _chords) {
            Integer newChord = ((chord + _changeBy) % 12 + 12) % 12;
            Log.d(TAG, "changeBy = " + _changeBy);
            Log.d(TAG, "chord was " + chord + ", changed to " + newChord);
            _newChords.add(newChord);
        }
        Log.d(TAG, _newChords.toString());
    }

}

