package com.example.alpha;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static com.example.alpha.R.string.navigation_drawer_close;
import static com.example.alpha.R.string.navigation_drawer_open;

public class PrincipaleActivity extends AppCompatActivity {
    ImageView menubutton;
    private DrawerLayout drawer;
   //ActionBar.NavigationMode navigationModeView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);

        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer  ,navigation_drawer_open, navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //navigationModeView=findViewById(R.id.nav__view);
        menubutton=findViewById(R.id.imageView37);
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();}
    }
}
