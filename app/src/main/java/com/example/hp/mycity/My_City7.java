package com.example.hp.mycity;
//3.6
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class My_City7 extends AppCompatActivity {
    public void clickCircuit(View view){
        Intent intent=new Intent(getApplicationContext(),cirlist.class);
        startActivity(intent);}

    public void clickCourt(View view){
        Intent intent=new Intent(getApplicationContext(),courtlist.class);
        startActivity(intent);}

    public void clickMunicipal(View view){
        Intent intent=new Intent(getApplicationContext(),munlist.class);
        startActivity(intent);}

    public void clickPol(View view){
        Intent intent=new Intent(getApplicationContext(),policelist.class);
        startActivity(intent);}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__city7);
    }
}
