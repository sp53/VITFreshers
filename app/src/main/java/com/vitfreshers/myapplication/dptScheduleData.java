package com.vitfreshers.myapplication;

import android.os.AsyncTask;

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

    public void fetchData(int i)
    {
        if(i==1)
        {
            u="https://vitappapi.herokuapp.com/schedule.php?reg="+SaveSharedPreference.getField("CurrentUser");
        }
        else
        {
            u="http://vitappapi.herokuapp.com/induction.php";
        }

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
                    date[i] = jo.getString("Date");
                    time[i] = jo.getString("Time");
                    venue[i] = jo.getString("Venue");
                    person[i] = jo.getString("ResPer");
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


}
