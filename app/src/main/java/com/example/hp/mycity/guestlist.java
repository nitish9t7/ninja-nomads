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

public class guestlist extends AppCompatActivity {
    ListView lv;
    FirebaseDatabase database;
    DatabaseReference ref,menuRef,guestRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
   guesthouse guest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestlist);
        guest=new guesthouse();
        String string=getString(R.string.City);
        lv=findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference(string);
        menuRef=ref.child("menu");
        guestRef=menuRef.child("guesthouse");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.guest_info,R.id.guestInfo,list);
        guestRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    guest= ds.getValue(guesthouse.class);
                    list.add("*"+guest.getName().toString() +"\n  " +guest.getAdd().toString() +"\n  " +guest.getPh().toString());

                }
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
