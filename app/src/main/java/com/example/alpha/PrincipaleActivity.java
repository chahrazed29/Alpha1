package com.example.alpha;
import android.accounts.Account;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import static com.example.alpha.R.string.navigation_drawer_close;
import static com.example.alpha.R.string.navigation_drawer_open;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class PrincipaleActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ImageView imgprofile;
    public static int id;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    DatabaseHelper mydb;
    SQLiteDatabase db;
   //private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);
        //drawer=findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer  ,navigation_drawer_open, navigation_drawer_close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();
        mydb = new DatabaseHelper(this);

        Intent mIntent = getIntent();
       final String sessionEmail = mIntent.getStringExtra("EXTRA_SESSION_EMAIL");

        imgprofile=  findViewById(R.id.profile);

        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent1 =new Intent(PrincipaleActivity.this, ProfileActivity.class);
                intent1.putExtra("EXTRA_SESSION_EMAIL",sessionEmail);
                startActivity(intent1);*/

               if(checkUser(sessionEmail)==0){
                    Intent intent1 =new Intent(PrincipaleActivity.this, ProfileActivity.class);
                    intent1.putExtra("EXTRA_SESSION_EMAIL",sessionEmail);
                     startActivity(intent1);
                   finish();

                }else{
                    Intent intent2 =new Intent(PrincipaleActivity.this, Entreprise_ProfileActivity.class);
                    intent2.putExtra("EXTRA_SESSION_EMAIL",sessionEmail);
                    startActivity(intent2);
                   finish();

                }
            }
        });
    }
        /*@Override
        public void onBackPressed() {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else{
                super.onBackPressed();}}*/

        public boolean isServicesOK(){
            Log.d(TAG, "isServicesOK: checking google services version");

            int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(PrincipaleActivity.this);

            if(available == ConnectionResult.SUCCESS){
                //everything is fine and the user can make map requests
                Log.d(TAG, "isServicesOK: Google Play Services is working");
                return true;
            }
            else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
                //an error occured but we can resolve it
                Log.d(TAG, "isServicesOK: an error occured but we can fix it");
                Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(PrincipaleActivity.this, available, ERROR_DIALOG_REQUEST);
                dialog.show();
            }else{
                Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
            }
            return false;
        }

      public int checkUser(String email){


            if(mydb.checkIndividueEmail(email)){
                return 0;
            }
                return 1;

        }

}