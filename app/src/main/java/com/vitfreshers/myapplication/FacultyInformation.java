package com.vitfreshers.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

public class FacultyInformation extends AppCompatActivity {

    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_information);

        Toolbar toolbar = findViewById(R.id.toolbar_fi);
        setSupportActionBar(toolbar);
        toolbar.bringToFront();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("VIT Freshers");

        ProgressBar pbr = findViewById(R.id.facultypbr);
        pbr.setVisibility(View.VISIBLE);


        ListView lv = findViewById(R.id.facultyLV);
        final FacultyAdapter facultyAdapter = new FacultyAdapter(this,pbr);
        lv.setAdapter(facultyAdapter);

        sv = findViewById(R.id.searchView);
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(facultyAdapter.loaded) {
                    sv.setIconified(false);
                }
            }
        });
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String sii) {
                if(facultyAdapter.loaded){
                    facultyAdapter.getFilter().filter(sii);
                }
                return false;
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
    public void onBackPressed() {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

}
