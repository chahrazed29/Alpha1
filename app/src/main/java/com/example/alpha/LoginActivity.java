package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    public TextView insecrireTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        insecrireTextView=(TextView)findViewById(R.id.textInscrire2);
        insecrireTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,IndEntActivity.class);
                startActivity(intent);
            }
        });


    }
}
