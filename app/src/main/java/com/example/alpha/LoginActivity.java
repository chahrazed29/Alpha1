package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import android.widget.Toast;




public class LoginActivity extends AppCompatActivity  {




     EditText EditTextEmail;
     EditText EditTextPassword;

     Button ButtonLogin;

     TextView textViewLinkRegister;

  InputValidation inputValidation;
    DatabaseHelper db;


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        inputValidation=new InputValidation(this);

        ButtonLogin = (Button) findViewById(R.id.button);
        textViewLinkRegister = (TextView) findViewById(R.id.textInscrire2);

        textViewLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,IndEntActivity.class);
                startActivity(registerIntent);
            }
        });
        EditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditTextPassword = (EditText) findViewById(R.id.editTextMdp);
        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verification()){
                    String email = EditTextEmail.getText().toString().trim();
                    String pwd = EditTextPassword.getText().toString().trim();
                    Boolean res = db.checkIndividue(email,pwd);
                    if(res == true)
                    {
                    Intent HomePage = new Intent(LoginActivity.this,PrincipaleActivity.class);
                    startActivity(HomePage);

                  finish(); }
                    else
                    {
                    Toast.makeText(LoginActivity.this,"Vérifiez votre Informations",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Vérifiez votre Informations",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    public boolean verification(){
        if ((!inputValidation.isInputEditTextFilled(EditTextEmail))||(!inputValidation.isInputEditTextEmail(EditTextEmail))||(!inputValidation.isInputEditTextFilled(EditTextPassword))) {
            return false;
        }

        return true ;

    }


}
