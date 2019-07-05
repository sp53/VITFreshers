package com.vitfreshers.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AntiRaggingActivity extends AppCompatActivity {

    private String[][] committee_list = {
            {"Dr. Premalatha L", "Professor", "SELECT", "premalatha.l@vit.ac.in", "7373438656"},
            {"Dr. Rajasekaran V.", "Asst. Professor", "SAS", "rajasekaran.v@vit.ac.in", "9894614338"},
            {"Dr. Bhaskara Rao", "Asso. Professor", "SMBS", "bhaskararao@vit.ac.in", "8148544770"},
            {"Dr. Balamurugan B J", "Asst. Professor(Sr.)", "SAS", "balamurugan.bj@vit.ac.in", "9710344355"},
            {"Dr. V.Prakash", "Asst. Professor", "SENSE", "Prakash.v@vit.ac.in", "9952084950"},
            {"Dr. Subhakara Reddy", "Professor", "VITSOL", "subhakara.reddy@vit.ac.in", "9491725679"},
            {"Mr. Mathivanan", "CSO", "Admin", "mathivanan.v@vit.ac.in", "9443442902"}
    };

    private String[][] counsellor_list = {
            {"Ms. Bhuvaneswari S", "Student Counselor", "Administration", "bhuvaneswari.s@vit.ac.in", "9791142617"},
            {"Dr. Maya Rathnasabapathy", "Asst. Director, Library", "Psychology", "maya.r@vit.ac.in", "9444333030"}
    };

    private LinearLayout list1, linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_ragging);

        Toolbar toolbar = findViewById(R.id.toolbar_antiragging);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("VIT Freshers");

        list1 = findViewById(R.id.anti_ragging_committee_list);

        for(int i=0; i<committee_list.length; i++) {
            View view = this.getLayoutInflater().inflate(R.layout.anti_ragging_cards, null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            int top = 16, bottom = 16, right = 16, left = 4;
            params.setMargins(left, top, right, bottom);
            view.setLayoutParams(params);

            TextView name = view.findViewById(R.id.name_anti_ragging);
            TextView designation = view.findViewById(R.id.designation_anti_ragging);
            TextView school = view.findViewById(R.id.school_anti_ragging);
            TextView email = view.findViewById(R.id.email_anti_ragging);

            name.setText("NAME: "+committee_list[i][0]);
            designation.setText("DESIGNATION: "+committee_list[i][1]);
            school.setText("SCHOOL: "+committee_list[i][2]);
            email.setText("EMAIL: "+committee_list[i][3]);

            final String mobNo = committee_list[i][4];

            Button call = view.findViewById(R.id.call_anti_ragging);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + mobNo));
                    startActivity(intent);

                }
            });

            list1.addView(view);
        }


        linearLayout = findViewById(R.id.anti_ragging_counsellor_list);

        for(int i=0; i<counsellor_list.length; i++) {
            View view = this.getLayoutInflater().inflate(R.layout.anti_ragging_cards, null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            int top = 16, bottom = 16, right = 16, left = 4;
            params.setMargins(left, top, right, bottom);
            view.setLayoutParams(params);

            TextView name = view.findViewById(R.id.name_anti_ragging);
            TextView designation = view.findViewById(R.id.designation_anti_ragging);
            TextView school = view.findViewById(R.id.school_anti_ragging);
            TextView email = view.findViewById(R.id.email_anti_ragging);

            name.setText("NAME: "+counsellor_list[i][0]);
            designation.setText("DESIGNATION: "+counsellor_list[i][1]);
            school.setText("SCHOOL: "+counsellor_list[i][2]);
            email.setText("EMAIL: "+counsellor_list[i][3]);

            final String mobNo = counsellor_list[i][4];

            Button call = view.findViewById(R.id.call_anti_ragging);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + mobNo));
                    startActivity(intent);
                }
            });

            linearLayout.addView(view);
        }


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
}
