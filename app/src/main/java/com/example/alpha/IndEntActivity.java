package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class  IndEntActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ind_ent);
    }
    public void btninscind (View view)
    {
        startActivity(new Intent(this,IndividuActivity.class));
    }
    public void btninscent (View view)
    {
        startActivity(new Intent(this,EntrepriseActivity.class));
    }
}
