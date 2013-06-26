// modulator android project
// by me

package com.timseal.modulator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.NumberPicker;
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

        NumberPicker np = (NumberPicker) findViewById(R.id.plusminus);
        np.setValue(0);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                CharSequence msg = "Changed from " + i + " to " + i2;
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void addChord(View v) {
        CharSequence chord = "";
        switch (v.getId()) {
            case R.id.A:
                chord = "A";
                break;
            case R.id.Bb:
                chord = "Bb";
                break;
            case R.id.B:
                chord = "B";
                break;
            case R.id.C:
                chord = "C";
                break;
            case R.id.Db:
                chord = "Db";
                break;
            case R.id.D:
                chord = "D";
                break;
            case R.id.Eb:
                chord = "Eb";
                break;
            case R.id.E:
                chord = "E";
                break;
            case R.id.F:
                chord = "F";
                break;
            case R.id.Gb:
                chord = "Gb";
                break;
            case R.id.G:
                chord = "G";
                break;
            case R.id.Ab:
                chord = "Ab";
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
