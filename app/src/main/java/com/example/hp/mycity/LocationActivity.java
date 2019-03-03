package com.example.hp.mycity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
public class LocationActivity extends AppCompatActivity {
    Button button;
    Button button2;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission((Manifest.permission.ACCESS_COARSE_LOCATION))
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                String city = hereLocation(location.getLatitude(), location.getLongitude());
                textView.setText(city);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(LocationActivity.this, "City Not Found", Toast.LENGTH_SHORT).show();
            }
        }

        clickLocation();

    }

    public void clickLocation() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    try {
                        String city = hereLocation(location.getLatitude(), location.getLongitude());
                        textView.setText(city);
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(LocationActivity.this, "City Not Found", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    private String hereLocation(double lat , double lon){
        String  cityName="";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try{
            addresses = geocoder.getFromLocation(lat,lon,10);
            if(addresses.size()>0){
                for(Address adr: addresses){
                    if(adr.getLocality() != null && adr.getLocality().length()>0){
                        cityName=adr.getLocality();
                        break;
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return cityName;
    }
}
