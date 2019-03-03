package com.example.hp.mycity;
//3.8
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City9 extends AppCompatActivity {
    public void clickHotel(View view){
        Intent intent=new Intent(getApplicationContext(),hotlist.class);
        startActivity(intent);}

    public void clickRest(View view){
        Intent intent=new Intent(getApplicationContext(),restlist.class);
        startActivity(intent);}

    public void clickGuest(View view){
        Intent intent=new Intent(getApplicationContext(),guestlist.class);
        startActivity(intent);}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city9);
    }
}
