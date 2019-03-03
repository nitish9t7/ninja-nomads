package com.example.hp.mycity;
//3.10
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City11 extends AppCompatActivity {
    public void clickPopulation(View view){
        Intent intent=new Intent(getApplicationContext(),poplist.class);
        startActivity(intent);}

    public void clickArea(View view){
        Intent intent=new Intent(getApplicationContext(),arealist.class);
        startActivity(intent);}

    public void clickLiteracy(View view){
        Intent intent=new Intent(getApplicationContext(),litlist.class);
        startActivity(intent);}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city11);
    }
}
