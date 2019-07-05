package com.vitfreshers.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText departement;
    private EditText regNo;
    String dep;
    final Context context = this;

    String regno;
    String JSON_STRING;
    String jsdata;
    JSONObject jsobject;
    JSONArray jsarray;
    String indata="";
    String regnoStatus="";
    ProgressBar pb;

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
                TextView cyber = dialog.findViewById(R.id.cyber);
                TextView ai = dialog.findViewById(R.id.ai);
                TextView mba = dialog.findViewById(R.id.mba);

                cse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("B.Tech CSE");
                        dialog.dismiss();
                    }
                });

                cyber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("B.Tech CSE (Cyber)");
                        dialog.dismiss();
                    }
                });

                ai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("B.Tech CSE (A.I.)");
                        dialog.dismiss();
                    }
                });

                eee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("B.Tech EEE");
                        dialog.dismiss();
                    }
                });

                ece.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("B.Tech ECE");
                        dialog.dismiss();
                    }
                });

                ecm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("B.Tech ECM");
                        dialog.dismiss();
                    }
                });

                civil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("B.Tech Civil");
                        dialog.dismiss();
                    }
                });

                mechanical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("B.Tech Mechanical");
                        dialog.dismiss();
                    }
                });

                mba.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        departement.setText("MBA");
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void login(View view) {

        if(!CheckNetwork.isInternetAvailable(this)) //returns true if internet available
        {
            Toast.makeText(this, "Please open your Internet Connection", Toast.LENGTH_LONG).show();
            return;
        }


        regno = regNo.getText().toString().trim();
        dep = departement.getText().toString().trim();
        pb=findViewById(R.id.progressBar);

        if(regno.isEmpty() || dep .isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_LONG).show();
            return;
        }
        pb.setVisibility(View.VISIBLE);
        getJSON();
    }

    // json data
    public void getJSON()
    {

        new BackgroundTask().execute();

    }
    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String json_url;
        @Override
        protected void onPreExecute() {

            String u="https://vitappapi.herokuapp.com/?reg="+regno;
            json_url=u;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url= new URL(json_url);
                HttpURLConnection httpURLConnection;
                httpURLConnection = (HttpURLConnection) url.openConnection();
                BufferedReader br =new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb=new StringBuilder();
                while((JSON_STRING=br.readLine())!=null)
                {
                    sb.append(JSON_STRING+"\n");
                }
                br.close();
                httpURLConnection.disconnect();
                return sb.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            indata=result;


            try {
                jsobject = new JSONObject(indata);
                regnoStatus="";
                JSONObject jo;


                jsarray = jsobject.getJSONArray("MAIN_ACTIVITY");
                jo = jsarray.getJSONObject(0);
                regnoStatus = jo.getString("Status");

                if(regnoStatus.equals("No")) {
                    Toast.makeText(getApplicationContext(), "Invalid Registration Number", Toast.LENGTH_LONG).show();
                    pb.setVisibility(View.INVISIBLE);
                    return;
                }

                String ProctorName = jo.getString("Proctor");
                String ProctorRoom = jo.getString("ProcVenue");

                SaveSharedPreference.setField("CurrentUser",regno);
                SaveSharedPreference.setField("CurrentUserProcName",ProctorName);
                SaveSharedPreference.setField("CurrentUserProcRoom",ProctorRoom);
                SaveSharedPreference.setField("CurrentUserDep",dep);

                Intent intent = new Intent(getApplicationContext(), AfterLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
