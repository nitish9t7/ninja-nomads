package com.example.hp.mycity;
//3.1
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City2 extends AppCompatActivity {
    public void clickSchool(View view){
        Intent intent=new Intent(getApplicationContext(),schoollist.class);
        startActivity(intent);}

    public void clickCollege(View view){
        Intent intent=new Intent(getApplicationContext(),collegelist.class);
        startActivity(intent);}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city2);
    }
}
