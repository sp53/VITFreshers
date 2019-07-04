package com.vitfreshers.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class AfterLoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);



        if(SaveSharedPreference.getField("CurrentUser") == null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.bringToFront();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("VIT Freshers");

        TextView tv = findViewById(R.id.textView);
        tv.setText("Hello, "+SaveSharedPreference.getField("CurrentUser"));


    }

    public void open_schedule_activity(View view) {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void open_maps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void show_proctor_name(View view) {

        Dialog dialog = new Dialog(AfterLoginActivity.this);
        dialog.setContentView(R.layout.proctor_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        TextView tv1=dialog.findViewById(R.id.procName);
        tv1.setText("Name : "+SaveSharedPreference.getField("CurrentUserProcName"));
        TextView tv2=dialog.findViewById(R.id.procRoom);
        tv2.setText("Room No. : "+SaveSharedPreference.getField("CurrentUserProcRoom"));

        dialog.show();


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

    public void logout(View view) {
        SaveSharedPreference.logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

}
