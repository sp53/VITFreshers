package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText departement;
    private EditText regNo;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        departement = findViewById(R.id.editText2);
        regNo = findViewById(R.id.editText);


        departement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("CHK", context.toString());

                regNo.clearFocus();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_layout);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                dialog.show();

                TextView cse = dialog.findViewById(R.id.cse);
                TextView eee = dialog.findViewById(R.id.eee);
                TextView ece = dialog.findViewById(R.id.ece);
                TextView mechanical = dialog.findViewById(R.id.mech);
                TextView ecm = dialog.findViewById(R.id.ecm);
                TextView civil = dialog.findViewById(R.id.civil);

                cse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("CSE");
                        dialog.dismiss();
                    }
                });

                eee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("EEE");
                        dialog.dismiss();
                    }
                });

                ece.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("ECE");
                        dialog.dismiss();
                    }
                });

                ecm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("ECM");
                        dialog.dismiss();
                    }
                });

                civil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("CIVIL");
                        dialog.dismiss();
                    }
                });

                mechanical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("MECHANICAL");
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void login(View view) {
        String regno = regNo.getText().toString().trim();
        String dep = departement.getText().toString().trim();

        if(regno.isEmpty() || dep .isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_LONG).show();
            return;
        }
        SaveSharedPreference.setUserName(regno);
        Intent intent = new Intent(this, AfterLoginActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
