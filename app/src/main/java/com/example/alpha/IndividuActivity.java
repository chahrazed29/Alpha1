package com.example.alpha;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class IndividuActivity extends AppCompatActivity {
 ImageView backbutton;
 EditText etUsername,etEmail,etPassword;
 DatabaseHelper mydb;
 SQLiteDatabase db;
    Button button;
    InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individu);
        inputValidation=new InputValidation(this);
        backbutton=findViewById(R.id.backbtn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mydb = new DatabaseHelper(this);
        etUsername = (EditText) findViewById(R.id.editText2);
        etEmail = (EditText) findViewById(R.id.editText4);
        etPassword = (EditText) findViewById(R.id.editText5);
        button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verification()){
                    String email = etEmail.getText().toString().trim();
                    if(!mydb.checkUserEmail(email)){
                    creatIndividue();
                    Intent intent=new Intent(IndividuActivity.this,PrincipaleActivity.class);
                    startActivity(intent);}
                    else{
                    Toast.makeText(IndividuActivity.this,"Email déjà utiliser",Toast.LENGTH_SHORT).show();
                    }
            }else{
                    Toast.makeText(IndividuActivity.this,"Vérifiez votre Informations",Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*etUsername=(EditText)findViewById(R.id.editText2);
        etEmail=(EditText)findViewById(R.id.editText4);
        etPassword=(EditText)findViewById(R.id.editText5);*/



    }

    public void creatIndividue(){

        mydb=new DatabaseHelper(this);
        db=mydb.getWritableDatabase();


        db.execSQL("INSERT INTO individue ("+mydb.INDIVIDUE_USER_NAME+","+mydb.INDIVIDUE_EMAIL+","+mydb.INDIVIDUE_PASS_WORD+") VALUES(?,?,?);"
                ,new String[]{etUsername.getText().toString(),etEmail.getText().toString(),etPassword.getText().toString()});

        Toast.makeText(this,"Compte creer",Toast.LENGTH_LONG).show();
    }

    public boolean verification(){
        if ((!inputValidation.isInputEditTextFilled(etUsername))||(!inputValidation.isInputEditTextFilled(etEmail))||(!inputValidation.isInputEditTextEmail(etEmail))||(!inputValidation.isInputEditTextFilled(etPassword))) {
            return false;
        }

        return true ;

    }
}
