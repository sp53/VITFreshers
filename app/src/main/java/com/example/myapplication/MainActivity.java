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

public class MainActivity extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText departement = findViewById(R.id.editText2);
        final EditText regNo = findViewById(R.id.editText);


        departement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CHK", context.toString());

                regNo.clearFocus();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_layout);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                TextView cse = dialog.findViewById(R.id.cse);
                cse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("CSE");
                        dialog.dismiss();
                    }
                });
                TextView eee = dialog.findViewById(R.id.eee);
                eee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("EEE");
                        dialog.dismiss();
                    }
                });
                TextView ece = dialog.findViewById(R.id.ece);
                ece.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("ECE");
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void login(View view) {
        Intent intent = new Intent(this, AfterLoginActivity.class);
        startActivity(intent);
        Log.d("CLICKED", "TRUE");
    }
}
