package com.example.alpha;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Spinner;

public class Entreprise_ProfileActivity extends AppCompatActivity {
Spinner spinnerentr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprise__profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addListenerOnSpinnerItemSelection();

    }
    public void addListenerOnSpinnerItemSelection() {
        spinnerentr = (Spinner) findViewById(R.id.spinner_entr);
        spinnerentr.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
