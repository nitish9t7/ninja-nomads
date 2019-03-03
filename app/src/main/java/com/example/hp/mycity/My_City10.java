package com.example.hp.mycity;
//3.9
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City10 extends AppCompatActivity {
    public void clickBank(View view){
        Intent intent=new Intent(getApplicationContext(),banklist.class);
        startActivity(intent);}

    public void clickAtm(View view){
        Intent intent=new Intent(getApplicationContext(),atmlist.class);
        startActivity(intent);}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city10);
    }
}
