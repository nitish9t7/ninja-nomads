package com.example.hp.mycity;
//3.5
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City6 extends AppCompatActivity {
    public void clickTemple(View view){
        Intent intent=new Intent(getApplicationContext(),templist.class);
        startActivity(intent);}

    public void clickMosque(View view){
        Intent intent=new Intent(getApplicationContext(),moslist.class);
        startActivity(intent);}

    public void clickGuru(View view){
        Intent intent=new Intent(getApplicationContext(),gurulist.class);
        startActivity(intent);}

    public void clickChurch(View view){
        Intent intent=new Intent(getApplicationContext(),churchlist.class);
        startActivity(intent);}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city6);
    }
}
