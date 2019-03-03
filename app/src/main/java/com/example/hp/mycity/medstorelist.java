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

public class medstorelist extends AppCompatActivity {
    ListView lv;
    FirebaseDatabase database;
    DatabaseReference ref,medRef,medstoreRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    medstore med;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medstorelist);
        med=new medstore();
        String string=getString(R.string.City);
        lv=findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference(string);
        medRef=ref.child("medical");
        medstoreRef=medRef.child("medstore");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.med_info,R.id.medInfo,list);
        medstoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    med= ds.getValue(medstore.class);
                    list.add("*"+med.getName().toString() +"\n  " +med.getAdd().toString() +"\n  " +med.getPh().toString());

                }
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

