package com.example.alpha;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.common.*;
import com.google.android.gms.common.GoogleApiAvailability;
import android.widget.Toast;
import static com.example.alpha.R.string.navigation_drawer_close;
import static com.example.alpha.R.string.navigation_drawer_open;
import android.widget.Button;
import android.content.Intent;
public class PrincipaleActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);

        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer  ,navigation_drawer_open, navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(isServicesOK()){
            init();
        }
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();}}
    private void init(){
        @SuppressLint("WrongViewCast") Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipaleActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }  public boolean isServicesOK(){
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
}
