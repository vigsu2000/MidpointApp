package com.example.vignesh.midpoint;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Geocoder;
import android.location.Address;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

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

    public void onMapSearch(View view) {
        EditText locationSearch1 = (EditText) findViewById(R.id.editText1);
        String location1 = locationSearch1.getText().toString();
        List<Address> addressList1 = null;

        if (location1 != null || !location1.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList1 = geocoder.getFromLocationName(location1, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList1.get(0);
            LatLng latLng1 = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng1).title("Marker"));
        }

        EditText locationSearch2 = (EditText) findViewById(R.id.editText2);
        String location2 = locationSearch2.getText().toString();
        List<Address> addressList2 = null;

        if (location2 != null || !location2.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList2 = geocoder.getFromLocationName(location2, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList2.get(0);
            LatLng latLng2 = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng2).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng2));
        }
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
    }
}
