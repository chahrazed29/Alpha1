package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class  IndEntActivity extends AppCompatActivity {
    public Button button;
    public Button button1;
    ImageView backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ind_ent);
        button=(Button)findViewById(R.id.buttonInd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndEntActivity.this,IndividuActivity.class);
                startActivity(intent);
            }
        });


         button1= (Button) findViewById(R.id.buttonEnt);
         button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( IndEntActivity.this,EntrepriseActivity.class);
                startActivity(intent);
            }
        });

        backbutton=findViewById(R.id.backbtn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
