package com.example.vignesh.midpoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        getMapScreenIntent.putExtra("location1", loc1.getText().toString());
        getMapScreenIntent.putExtra("location2", loc2.getText().toString());
        startActivity(getMapScreenIntent);
    }
}
