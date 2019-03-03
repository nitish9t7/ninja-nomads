package com.example.hp.mycity;
//3.7
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City8 extends AppCompatActivity {
    public void clickOld(View view){
        Intent intent=new Intent(getApplicationContext(),oldlist.class);
        startActivity(intent);}

    public void clickOrphanage(View view){
        Intent intent=new Intent(getApplicationContext(),orphlist.class);
        startActivity(intent);}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city8);
    }
}
