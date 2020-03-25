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


public class EntrepriseActivity extends AppCompatActivity {
    ImageView backbutton;
    EditText etUsername,etNRC,etEmail,etPassword;
    DatabaseHelper mydb;
    SQLiteDatabase db;
    Button button;
    InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprise);
        inputValidation=new InputValidation(this);
        backbutton=findViewById(R.id.backbtn2);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mydb = new DatabaseHelper(this);
        etUsername = (EditText) findViewById(R.id.editText8);
        etNRC = (EditText) findViewById(R.id.editText10);
        etEmail = (EditText) findViewById(R.id.editText11);
        etPassword = (EditText) findViewById(R.id.editText13);
        button=(Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verification()){
                    String email = etEmail.getText().toString().trim();
                    if(!mydb.checkUserEmail(email)){
                        creatEntreprise();
                        Intent intent=new Intent(EntrepriseActivity.this,PrincipaleActivity.class);
                        startActivity(intent);}
                    else{
                        Toast.makeText(EntrepriseActivity.this,"Email déjà utiliser",Toast.LENGTH_SHORT).show();
                    }}
                else{
                    Toast.makeText(EntrepriseActivity.this,"Vérifiez votre Informations",Toast.LENGTH_SHORT).show();
                }


            }
        });





    }

    public void creatEntreprise(){

        mydb=new DatabaseHelper(this);
        db=mydb.getWritableDatabase();


        db.execSQL("INSERT INTO entreprise ("+mydb.ENTREPRISE_USER_NAME+","+mydb.ENTREPRISE_NRC+","+mydb.ENTREPRISE_EMAIL+","+mydb.ENTREPRISE_PASS_WORD+") VALUES(?,?,?,?);"
                ,new String[]{etUsername.getText().toString(),etNRC.getText().toString(),etEmail.getText().toString(),etPassword.getText().toString()});

        Toast.makeText(this,"Compte creer",Toast.LENGTH_LONG).show();
    }

   public boolean verification(){
        if ((!inputValidation.isInputEditTextFilled(etUsername))||(!inputValidation.isInputEditTextFilled(etNRC))||(!inputValidation.isInputEditTextFilled(etEmail))||(!inputValidation.isInputEditTextEmail(etEmail))||(!inputValidation.isInputEditTextFilled(etPassword))) {
            return false;
        }

        return true ;

    }
}