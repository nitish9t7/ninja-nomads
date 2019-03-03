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

public class churchlist extends AppCompatActivity {
    ListView lv;
    FirebaseDatabase database;
    DatabaseReference ref,prayerRef,chRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    church ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_churchlist);
       ch=new church();
        String string=getString(R.string.City);
        lv=findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference(string);
        prayerRef=ref.child("prayer");
       chRef=prayerRef.child("church");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.church_info,R.id.churchInfo,list);
        chRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    ch= ds.getValue(church.class);
                    list.add("*"+ch.getName().toString() +"\n  " +ch.getAdd().toString() +"\n  " +ch.getPh().toString());

                }
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
