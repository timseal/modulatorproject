package com.timseal.modulator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

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

    private void addChord(Integer chordNumber) {
        chords.add(chordNumber);
        updateChordDisplay(chordNumber.toString());
    }

    private void updateChordDisplay(CharSequence chord) {
        TextView chordsView = (TextView) findViewById(R.id.chords);
        chordsView.append(chord);
    }
}
