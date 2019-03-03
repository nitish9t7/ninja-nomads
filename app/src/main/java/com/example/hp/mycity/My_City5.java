package com.example.hp.mycity;
//3.4
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City5 extends AppCompatActivity {
    public void clickRail(View view){
        Intent intent=new Intent(getApplicationContext(),raillist.class);
        startActivity(intent);}

    public void clickBus(View view){
        Intent intent=new Intent(getApplicationContext(),buslist.class);
        startActivity(intent);}

    public void clickAir(View view){
        Intent intent=new Intent(getApplicationContext(),airlist.class);
        startActivity(intent);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city5);
    }
}
