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

public class banklist extends AppCompatActivity {
    ListView lv;
    FirebaseDatabase database;
    DatabaseReference ref,banksRef,bankRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    bank ban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banklist);
        ban=new bank();
        String string=getString(R.string.City);
        lv=findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference(string);
        banksRef=ref.child("banks");
        bankRef=banksRef.child("bank");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.bank_info,R.id.bankInfo,list);
        bankRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    ban = ds.getValue(bank.class);
                    list.add("*"+ban.getName().toString() +"\n  " +ban.getAdd().toString() +"\n  " +ban.getPh().toString());

                }
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
