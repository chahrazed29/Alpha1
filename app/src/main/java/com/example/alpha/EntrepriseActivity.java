package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EntrepriseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprise);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
