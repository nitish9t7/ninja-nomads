package com.example.hp.mycity;
//3.3
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City4 extends AppCompatActivity {
    public void clickZoo(View view){
        Intent intent=new Intent(getApplicationContext(),zoolist.class);
        startActivity(intent);}

    public void clickMuseum(View view){
        Intent intent=new Intent(getApplicationContext(),museumlist.class);
        startActivity(intent);}

    public void clickPlanet(View view){
        Intent intent=new Intent(getApplicationContext(),planetlist.class);
        startActivity(intent);}

    public void clickMonument(View view){
        Intent intent=new Intent(getApplicationContext(),monulist.class);
        startActivity(intent);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city4);
    }
}
