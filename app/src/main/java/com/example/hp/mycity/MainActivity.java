package com.example.hp.mycity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById( R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                    new HomeFragment ()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment ();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new FavoritesFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
    public void clickEducation(View view){
        Intent intent=new Intent(getApplicationContext(), My_City2.class);
        startActivity(intent);
    }
    public void clickMed(View view){
        Intent intent=new Intent(getApplicationContext(), My_City3.class);
        startActivity(intent);
    }
    public void clickPark(View view){
        Intent intent=new Intent(getApplicationContext(), My_City4.class);
        startActivity(intent);
    }
    public void clickTravel(View view){
        Intent intent=new Intent(getApplicationContext(), My_City5.class);
        startActivity(intent);
    }
    public void clickPrayer(View view){
        Intent intent=new Intent(getApplicationContext(), My_City6.class);
        startActivity(intent);
    }
    public void clickOfficial(View view){
        Intent intent=new Intent(getApplicationContext(), My_City7.class);
        startActivity(intent);
    }
    public void clickUnofficial(View view){
        Intent intent=new Intent(getApplicationContext(), My_City8.class);
        startActivity(intent);
    }
    public void clickMenu(View view){
        Intent intent=new Intent(getApplicationContext(), My_City9.class);
        startActivity(intent);
    }
    public void clickBank(View view){
        Intent intent=new Intent(getApplicationContext(), My_City10.class);
        startActivity(intent);
    }
    public void clickSurvey(View view){
        Intent intent=new Intent(getApplicationContext(), My_City11.class);
        startActivity(intent);
    }




}

















