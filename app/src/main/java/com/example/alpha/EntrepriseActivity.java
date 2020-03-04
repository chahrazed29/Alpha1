package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EntrepriseActivity extends AppCompatActivity {
   ImageView BackButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprise);

      BackButton2=findViewById(R.id.backbtn2);
        BackButton2.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
              finish();
            }
       });
    }
}
