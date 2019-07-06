package com.vitfreshers.myapplication;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class dptScheduleData
{
    public  int length;
    public  String name[];
    public  String date[];
    public  String time[];
    public  String venue[];
    public  String person[];



    String JSON_STRING;
    String jsdata;
    JSONObject jsobject;
    JSONArray jsarray;
    String indata="";
    String u="";
    ViewGroup vg;



    public void fetchData(int i, ViewGroup v)
    {
        vg=v;
        if(i==1)
        {
            u="http://vitappapi.herokuapp.com/schedule.php?brnch=";
            switch(SaveSharedPreference.getField("CurrentUserDep"))
            {
                case "B.Tech CSE":
                    u=u+"BCE";
                    break;
                case "B.Tech CSE (Cyber)":
                    u=u+"BCE";
                    break;
                case "B.Tech CSE (A.I.)":
                    u=u+"BCE";
                    break;
                case "B.Tech EEE":
                    u=u+"BEE";
                    break;
                case "B.Tech ECE":
                    u=u+"BEC";
                    break;
                case "B.Tech ECM":
                    u=u+"BLC";
                    break;
                case "B.Tech Civil":
                    u=u+"BCL";
                    break;
                case "B.Tech Mechanical":
                    u=u+"BME";
                    break;
                case "MBA":
                    u=u+"MBA";
                    break;
                default:
                    u=u+"BCE";
                    break;
            }

        }
        else
        {
            u="http://vitappapi.herokuapp.com/induction.php";

        }

        getJSON();
    }

    // json data
    public void getJSON() {

        String s="";
        try{
            s=new BackgroundTask().execute().get();
        }
        catch (Exception e)
        {

        }

        indata=s;


        try {
            jsobject = new JSONObject(indata);
            JSONObject jo;


            jsarray = jsobject.getJSONArray("Schedule");
            length=jsarray.length();
            name=new String[length];
            time=new String[length];
            date=new String[length];
            venue=new String[length];
            person=new String[length];

            for(int i=0;i<length;i++)
            {
                jo = jsarray.getJSONObject(i);
                name[i]= jo.getString("Name");
                date[i] = "DATE : "+jo.getString("Date");
                time[i] = "TIME : "+jo.getString("Time");
                venue[i] = "VENUE : "+jo.getString("Venue");
                person[i] = "SPEAKER : "+jo.getString("ResPer");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String json_url;
        @Override
        protected void onPreExecute() {


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
                JSONObject jo;


                jsarray = jsobject.getJSONArray("Schedule");
                length=jsarray.length();
                name=new String[length];
                time=new String[length];
                date=new String[length];
                venue=new String[length];
                person=new String[length];

                for(int i=0;i<length;i++)
                {
                    jo = jsarray.getJSONObject(i);
                    name[i]= jo.getString("Name");
                    date[i] = "DATE : "+jo.getString("Date");
                    time[i] = "TIME : "+jo.getString("Time");
                    venue[i] = "VENUE : "+jo.getString("Venue");
                    person[i] = "SPEAKER : "+jo.getString("ResPer");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


}
