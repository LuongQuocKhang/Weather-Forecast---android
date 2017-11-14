package com.example.luongquockhang.weatherforecast.WeatherPrediction;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.luongquockhang.weatherforecast.R;
import com.example.luongquockhang.weatherforecast.utils.WeatherAsyncTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void addEvent()
    {
        if ( mMap == null )
        {
            return;
        }
        else
        {
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    mMap.clear();
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude,latLng.longitude),13));
                    CameraPosition cameraposotion = new CameraPosition.Builder().target(new LatLng(latLng.latitude,latLng.longitude))
                            .zoom(5) // sets the zoom
                            .bearing(45) // sets orientation of the camera to east
                            .tilt(30) // sets the tilt of the camera to 30 degree
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraposotion));

                    MarkerOptions option = new MarkerOptions();
                    option.position(new LatLng(latLng.latitude,latLng.longitude));
                    option.title("....").snippet("....!");
                    option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    Marker marker = mMap.addMarker(option);

                    WeatherAsyncTask Task = new WeatherAsyncTask(MapsActivity.this,latLng.latitude,latLng.longitude,marker,mMap);
                    Task.execute();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //addEvent();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        addEvent();
    }
}
