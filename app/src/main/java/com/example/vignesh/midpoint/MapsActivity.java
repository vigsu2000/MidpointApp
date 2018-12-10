package com.example.vignesh.midpoint;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;
import android.location.Geocoder;
import android.location.Address;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    int PLACE_PICKER_REQUEST = 1;

    private GoogleMap mMap;
    private Address location1, location2;
    private SeekBar seekBar;
    private LatLng meetUpPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent activityThatCalled = getIntent();
        location1 = (Address) activityThatCalled.getExtras().get("address1");
        location2 = (Address) activityThatCalled.getExtras().get("address2");
        seekBar = findViewById(R.id.seek_bar);
        seekBar.setProgress(50);
    }

    public void onMapSearch(View view) {
        mMap.clear();

        List<Address> addressList1 = null;

        LatLng latLng1 = null;
        LatLng latLng2 = null;

        latLng1 = new LatLng(location1.getLatitude(), location1.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng1).title("Location 1"));

        latLng2 = new LatLng(location2.getLatitude(), location2.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng2).title("Location 2"));

        double distanceFromLoc1VsLoc2 = seekBar.getProgress();
        distanceFromLoc1VsLoc2 = distanceFromLoc1VsLoc2 / 100;

        meetUpPoint = SphericalUtil.interpolate(latLng1, latLng2, distanceFromLoc1VsLoc2);

        mMap.addMarker(new MarkerOptions().position(meetUpPoint).title("Meet Up Point"));

        mMap.animateCamera(CameraUpdateFactory.newLatLng(meetUpPoint));

    }

    public void goPlacePicker(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        builder.setLatLngBounds(new LatLngBounds(meetUpPoint, meetUpPoint));
        try {
            startActivityForResult(builder.build(MapsActivity.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(MapsActivity.this, data);
                System.out.println(place.getAddress());
            }
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
        mMap.clear();

        LatLng latLng1 = null;
        LatLng latLng2 = null;

        latLng1 = new LatLng(location1.getLatitude(), location1.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng1).title("Location 1"));

        latLng2 = new LatLng(location2.getLatitude(), location2.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng2).title("Location 2"));

        meetUpPoint = SphericalUtil.interpolate(latLng1, latLng2, .5);

        mMap.addMarker(new MarkerOptions().position(meetUpPoint).title("Meet Up Point"));

        mMap.animateCamera(CameraUpdateFactory.newLatLng(meetUpPoint));
    }
}
