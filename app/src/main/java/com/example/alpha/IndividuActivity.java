package com.example.alpha;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class IndividuActivity extends AppCompatActivity {
    ImageView backbutton;
    EditText etUsername, etEmail, etPassword;
    DatabaseHelper mydb;
    SQLiteDatabase db;
    Button button;
    InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individu);
        inputValidation = new InputValidation(this);
        backbutton = findViewById(R.id.backbtn);
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
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verification()) {
                    String email = etEmail.getText().toString().trim();
                    if (!mydb.checkUserEmail(email)) {
                        creatIndividue();

                        Intent intent = new Intent(IndividuActivity.this, PrincipaleActivity.class);
                             
                        startActivity(intent);

                        finish();
                    } else {
                        Toast.makeText(IndividuActivity.this, "Email déjà utiliser", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(IndividuActivity.this, "Vérifiez votre Informations", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*etUsername=(EditText)findViewById(R.id.editText2);
        etEmail=(EditText)findViewById(R.id.editText4);
        etPassword=(EditText)findViewById(R.id.editText5);*/


    }

    public int getid(String email) {
        String query = "SELECT " + mydb.INDIVIDUE_ID_NUM + "FROM " + mydb.INDIVIDUE_TABLE + " WHERE "
                + mydb.INDIVIDUE_EMAIL + " = '" + email + "'";
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor res = db.rawQuery(query, null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    return Integer.parseInt(res.getString(res.getColumnIndex(mydb.INDIVIDUE_ID_NUM))); // if your column name is rowid then replace id with rowid
                } while (res.moveToNext());
            }
        } else {
            Toast.makeText(getBaseContext(), "cursor is null", Toast.LENGTH_LONG).show();

          }    return -1;
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
