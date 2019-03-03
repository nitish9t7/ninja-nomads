package com.example.hp.mycity;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class FavoritesFragment extends Fragment {
    TextView selectCity, cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    ProgressBar loader;
    Typeface weatherFont;
    String city ;
    String OPEN_WEATHER_MAP_API = "d5dc2bfc6d73d66d2b6dc13921adbd4e";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate( R.layout.fragment_favorites, container, false);
        loader = (ProgressBar) rootView.findViewById( R.id.loader);
        selectCity = (TextView) rootView.findViewById( R.id.selectCity);
        cityField = (TextView) rootView.findViewById( R.id.city_field);
        updatedField = (TextView) rootView.findViewById( R.id.updated_field);
        detailsField = (TextView) rootView.findViewById( R.id.details_field);
        currentTemperatureField = (TextView) rootView.findViewById( R.id.current_temperature_field);
        humidity_field = (TextView) rootView.findViewById( R.id.humidity_field);
        pressure_field = (TextView) rootView.findViewById( R.id.pressure_field);
        weatherIcon = (TextView) rootView.findViewById( R.id.weather_icon);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons-regular-webfont.ttf");
        weatherIcon.setTypeface(weatherFont);

        taskLoadUp(city);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission((Manifest.permission.ACCESS_COARSE_LOCATION))
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION },1000);
        }
        else{
            LocationManager locationManager =(LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                String city1 = hereLocation(location.getLatitude(), location.getLongitude());
                city = city1.toString();
                FavoritesFragment.DownloadWeather task = new FavoritesFragment.DownloadWeather();
                task.execute(city);

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity(), "City Not Found", Toast.LENGTH_SHORT).show();
            }
        }

        selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Change City");
                final EditText input = new EditText(getActivity());
                input.setText(city);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Change",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                city = input.getText().toString();
                                taskLoadUp(city);
                            }
                        });
                alertDialog.setNegativeButton("Cancel", ( dialog, which ) -> dialog.cancel() );
                alertDialog.show();
            }
        });
        return inflater.inflate( R.layout.fragment_favorites, container, false);
    }


    public void taskLoadUp(String query) {
        if (!Function.isNetworkAvailable ( Objects.requireNonNull ( getActivity ( ) ).getApplicationContext ( ) )) {
            Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        } else {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        }
    }



    class DownloadWeather extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);

        }
        protected String doInBackground(String...args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();

                    cityField.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    detailsField.setText(details.getString("description").toUpperCase(Locale.US));
                    currentTemperatureField.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    humidity_field.setText("Humidity: " + main.getString("humidity") + "%");
                    pressure_field.setText("Pressure: " + main.getString("pressure") + " hPa");
                    updatedField.setText(df.format(new Date(json.getLong("dt") * 1000)));
                    weatherIcon.setText(Html.fromHtml( Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));

                    loader.setVisibility(View.GONE);

                }
            } catch (JSONException e) {
                //Toast.makeText(getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }


        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case  1000:{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    LocationManager locationManager =(LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    try {
                        String city1 = hereLocation(location.getLatitude(), location.getLongitude());
                        city = city1.toString();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "City Not Found", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getActivity(), "Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private String hereLocation(double lat , double lon){
        String  cityName="";


        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
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

