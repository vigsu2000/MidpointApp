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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onFindMidpointClick(View view) {
        Intent getMapScreenIntent = new Intent(this, MapsActivity.class);
        EditText loc1 = findViewById(R.id.loc1);
        EditText loc2 = findViewById(R.id.loc2);
        String location1 = loc1.getText().toString();
        String location2 = loc2.getText().toString();
        List<Address> addressList1 = null;
        if (location1 != null && !location1.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList1 = geocoder.getFromLocationName(location1, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<Address> addressList2 = null;
        if (location2 != null && !location2.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList2 = geocoder.getFromLocationName(location2, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (addressList1 != null && addressList1.size() > 0
                && addressList2 != null && addressList2.size() > 0) {
            getMapScreenIntent.putExtra("address1", addressList1.get(0));
            getMapScreenIntent.putExtra("address2", addressList2.get(0));
            startActivity(getMapScreenIntent);
        } else {
            Toast message = Toast.makeText(getApplicationContext(), "Invalid Location(s)", Toast.LENGTH_SHORT);
            message.show();
        }
    }
}
