package com.example.hp.mycity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class moslist extends AppCompatActivity {
    ListView lv;
    FirebaseDatabase database;
    DatabaseReference ref,prayerRef,mosRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
   mosque mos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moslist);
        mos=new mosque();
        String string=getString(R.string.City);
        lv=findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference(string);
        prayerRef=ref.child("prayer");
        mosRef=prayerRef.child("mosque");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.mos_info,R.id.mosInfo,list);
        mosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    mos = ds.getValue(mosque.class);
                    list.add("*"+mos.getName().toString() +"\n  " +mos.getAdd().toString() +"\n  " +mos.getPh().toString());

                }
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
