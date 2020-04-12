package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    public Button loginButton;
    public TextView insecrireTextView;
    private Object LoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insecrireTextView=(TextView)findViewById(R.id.textInscrire);
        insecrireTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,IndEntActivity.class);
                startActivity(intent);
                finish();
               // finishFromChild((Activity) LoginActivity);


            }
        });


        loginButton=(Button)findViewById(R.id.buttonConnecter);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });











    }



}
