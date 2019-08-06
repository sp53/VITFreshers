package com.vitfreshers.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class FacultyInformation extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_information);

        Toolbar toolbar = findViewById(R.id.toolbar_fi);
        setSupportActionBar(toolbar);
        toolbar.bringToFront();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("VIT Freshers");

        final EditText schools = findViewById(R.id.school_edit_text);
        schools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog bd=new Dialog(context);
                bd.setContentView(R.layout.school_dialog);
                bd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bd.show();


                TextView scse=bd.findViewById(R.id.scse);
                TextView sense=bd.findViewById(R.id.sense);
                TextView smbs=bd.findViewById(R.id.smbs);
                TextView sas=bd.findViewById(R.id.sas);
                TextView law=bd.findViewById(R.id.law);

                scse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        schools.setText("SCSE");
                        bd.dismiss();
                    }
                });

                sense.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        schools.setText("SENSE");
                        bd.dismiss();
                    }
                });

                smbs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        schools.setText("SMBS");
                        bd.dismiss();
                    }
                });

                sas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        schools.setText("SAS");
                        bd.dismiss();
                    }
                });

                law.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        schools.setText("LAW");
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            super.onKeyDown(keyCode, event);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
