package com.vitfreshers.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MarkerOptions mo;
    final Context context = this;
    private FusedLocationProviderClient fusedLocationClient;



    Map<String,double[]> ltlng = new HashMap<String, double[]>();
    LatLng vit = new LatLng( 12.840722, 80.153431 );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(!serviceCheck())
        {
            finish();
        }

        if(!CheckNetwork.isInternetAvailable(this)) //returns true if internet available
        {
            Toast.makeText(this, "Please open your Internet Connection", Toast.LENGTH_LONG).show();

        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        ltlng.put("ab1",new double[] {12.843967, 80.153321});
        ltlng.put("ab2",new double[] {12.842953, 80.156534});
        ltlng.put("adm",new double[] {12.840756, 80.153951});
        ltlng.put("lib",new double[] {12.841054, 80.153903});
        ltlng.put("whb",new double[] {12.842016, 80.156993});
        ltlng.put("mha",new double[] {12.844428, 80.152452});
        ltlng.put("mhb",new double[] {12.841902, 80.157393});
        ltlng.put("mhc",new double[] {12.843047, 80.157417});
        ltlng.put("vmart",new double[] {12.844885, 80.153866});
        ltlng.put("ibank",new double[] {12.844658, 80.153718});
        ltlng.put("kvb",new double[] {12.841593, 80.156613});
        ltlng.put("gazebo",new double[] {12.841828, 80.154360});
        ltlng.put("cts",new double[] {12.843756, 80.153671});

        Toolbar toolbar = findViewById(R.id.toolbar_maps);
        setSupportActionBar(toolbar);
        toolbar.bringToFront();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("VIT Freshers");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final EditText building= findViewById(R.id.building);
        building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog bd=new Dialog(context);
                bd.setContentView(R.layout.building_dialog);
                bd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bd.show();


                TextView ab1=bd.findViewById(R.id.ab1);
                TextView ab2=bd.findViewById(R.id.ab2);
                TextView adm=bd.findViewById(R.id.adm);
                TextView lib=bd.findViewById(R.id.lib);
                TextView whb=bd.findViewById(R.id.WHB);
                TextView mha=bd.findViewById(R.id.MHA);
                TextView mhb=bd.findViewById(R.id.MHB);
                TextView mhc=bd.findViewById(R.id.MHC);
                TextView vmart=bd.findViewById(R.id.vmart);
                TextView ibank=bd.findViewById(R.id.ibank);
                TextView kvb=bd.findViewById(R.id.kvb);
                TextView gazebo=bd.findViewById(R.id.gazebo);
                TextView cts=bd.findViewById(R.id.cts);

                ab1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Academic Block 1");
                        vit = new LatLng(ltlng.get("ab1")[0],ltlng.get("ab1")[1]);
                        onMapChange("Academic Block 1",ltlng.get("ab1")[0],ltlng.get("ab1")[1]);
                        bd.dismiss();
                    }
                });

                ab2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Academic Block 2");
                        vit = new LatLng(ltlng.get("ab2")[0],ltlng.get("ab2")[1]);
                        onMapChange("Academic Block 2",ltlng.get("ab2")[0],ltlng.get("ab2")[1]);
                        bd.dismiss();
                    }
                });

                adm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Admin Block");
                        vit = new LatLng(ltlng.get("adm")[0],ltlng.get("adm")[1]);
                        onMapChange("Admin Block",ltlng.get("adm")[0],ltlng.get("adm")[1]);
                        bd.dismiss();
                    }
                });

                lib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Library");
                        vit = new LatLng(ltlng.get("lib")[0],ltlng.get("lib")[1]);
                        onMapChange("Library",ltlng.get("lib")[0],ltlng.get("lib")[1]);
                        bd.dismiss();
                    }
                });

                whb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Girls Hostel B Block");
                        vit = new LatLng(ltlng.get("whb")[0],ltlng.get("whb")[1]);
                        onMapChange("Girls Hostel B Block",ltlng.get("whb")[0],ltlng.get("whb")[1]);
                        bd.dismiss();
                    }
                });

                mha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Boys Hostel A Block");
                        vit = new LatLng(ltlng.get("mha")[0],ltlng.get("mha")[1]);
                        onMapChange("Boys Hostel A Block",ltlng.get("mha")[0],ltlng.get("mha")[1]);
                        bd.dismiss();
                    }
                });

                mhb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Boys Hostel B Block");
                        vit = new LatLng(ltlng.get("mhb")[0],ltlng.get("mhb")[1]);
                        onMapChange("Boys Hostel B Block",ltlng.get("mhb")[0],ltlng.get("mhb")[1]);
                        bd.dismiss();
                    }
                });

                mhc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Boys Hostel C Block");
                        vit = new LatLng(ltlng.get("mhc")[0],ltlng.get("mhc")[1]);
                        onMapChange("Boys Hostel c Block",ltlng.get("mhc")[0],ltlng.get("mhc")[1]);
                        bd.dismiss();
                    }
                });

                vmart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("V-mart");
                        vit = new LatLng(ltlng.get("vmart")[0],ltlng.get("vmart")[1]);
                        onMapChange("V-mart",ltlng.get("vmart")[0],ltlng.get("vmart")[1]);
                        bd.dismiss();
                    }
                });

                ibank.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Indian Bank");
                        vit = new LatLng(ltlng.get("ibank")[0],ltlng.get("ibank")[1]);
                        onMapChange("Indian Bank",ltlng.get("ibank")[0],ltlng.get("ibank")[1]);
                        bd.dismiss();
                    }
                });

                kvb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("KVB Bank");
                        vit = new LatLng(ltlng.get("kvb")[0],ltlng.get("kvb")[1]);
                        onMapChange("KVB Bank",ltlng.get("kvb")[0],ltlng.get("kvb")[1]);
                        bd.dismiss();
                    }
                });

                gazebo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("Gazebo");
                        vit = new LatLng(ltlng.get("gazebo")[0],ltlng.get("gazebo")[1]);
                        onMapChange("Gazebo",ltlng.get("gazebo")[0],ltlng.get("gazebo")[1]);
                        bd.dismiss();
                    }
                });

                cts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        building.setText("CTS - AB1 (1st Floor)");
                        vit = new LatLng(ltlng.get("cts")[0],ltlng.get("cts")[1]);
                        onMapChange("CTS - AB1 (1st Floor)",ltlng.get("cts")[0],ltlng.get("cts")[1]);
                        bd.dismiss();
                    }
                });

            }
        });

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
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker)
            {

                if(isGoogleMapsInstalled())
                {
                    Uri mydir = Uri.parse("google.navigation:q=12.840722,80.153431");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mydir);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
                else
                {
                    Toast.makeText(MapsActivity.this, "Google Maps not installed on your mobile.", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
        /* Do not REMOVE these comments .....

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(vit);
        LatLngBounds bounds = builder.build();
        CameraUpdate center= CameraUpdateFactory.newLatLngBounds(bounds,0);

        */
        CameraUpdate center=CameraUpdateFactory.newLatLngZoom(vit,16.0f);
        mo = new MarkerOptions().position(vit).title("VIT Chennai");
        Marker m = mMap.addMarker(mo);
        m.showInfoWindow();
        mMap.moveCamera(center);

        //CameraUpdate zoom=CameraUpdateFactory.zoomTo(18);
        //mMap.animateCamera(zoom);

    }

    public void onMapChange(String s ,double lat ,double longi)
    {
        mMap.clear();
        final String lat_for_direction=""+lat;
        final String long_for_direction=""+longi;


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker)
            {

                if(isGoogleMapsInstalled())
                {
                    Uri mydir = Uri.parse("google.navigation:q="+lat_for_direction+","+long_for_direction);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mydir);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
                else
                {
                    Toast.makeText(MapsActivity.this, "Google Maps not installed on your mobile.", Toast.LENGTH_LONG).show();
                }

                return true;
            }
        });
        CameraUpdate center=CameraUpdateFactory.newLatLngZoom(vit,16.0f);
        mo = new MarkerOptions().position(vit).title(s);
        Marker m= mMap.addMarker(mo);
        m.showInfoWindow();
        mMap.moveCamera(center);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public boolean isGoogleMapsInstalled()
    {
        try
        {
            ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0 );
            return true;
        }
        catch(PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

}
