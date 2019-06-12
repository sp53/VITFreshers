package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(!serviceCheck())
        {
            finish();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.bringToFront();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("VIT Freshers");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng vit = new LatLng(12.843967, 80.153321);

        /* Do not REMOVE these comments .....

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(vit);
        LatLngBounds bounds = builder.build();
        CameraUpdate center= CameraUpdateFactory.newLatLngBounds(bounds,0);

        */
        CameraUpdate center=CameraUpdateFactory.newLatLngZoom(vit,18.0f);
        mMap.addMarker(new MarkerOptions().position(vit).title("VIT Chennai"));
        mMap.moveCamera(center);

        //CameraUpdate zoom=CameraUpdateFactory.zoomTo(18);
        //mMap.animateCamera(zoom);

    }

    public boolean serviceCheck()
    {
        int avail = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsActivity.this);

        if(avail == ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(avail))
        {
            Dialog mapd=GoogleApiAvailability.getInstance().getErrorDialog(MapsActivity.this,avail,9001);
            mapd.show();
        }
        else
        {
            Toast.makeText(this,"You can't make map request due Google Play Service Conflicts", Toast.LENGTH_LONG).show();
        }
        return false;

    }
}
