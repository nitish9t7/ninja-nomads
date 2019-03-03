package com.example.hp.mycity;
//3.2
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City3 extends AppCompatActivity {
    public void clickHospital(View view){
        Intent intent=new Intent(getApplicationContext(),hospitallist.class);
        startActivity(intent);}

    public void clickMedstore(View view){
        Intent intent=new Intent(getApplicationContext(),medstorelist.class);
        startActivity(intent);}

    public void clickBloodbank(View view){
        Intent intent=new Intent(getApplicationContext(),bloodlist.class);
        startActivity(intent);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city3);
    }
}
