package com.vitfreshers.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {
    private Activity activity;

    private LayoutInflater layoutInflater;
    ProgressBar pbrobj;
    ArrayList<EventStore> mainList = new ArrayList<EventStore>();


    String name;
    String description;
    String venue;
    String time;
    String date;
    String coord;
    String contact;


    int length=0;

    public EventAdapter(Activity activity, ProgressBar obj) {
        this.activity = activity;
        this.layoutInflater = activity.getLayoutInflater();
        pbrobj=obj;
        new BackgroundTask().execute();
    }

    @Override
    public int getCount() { return length;}

    @Override
    public Object getItem(int i) {
        return null;
    }

    public void notifyD()
    {
        super.notifyDataSetChanged();
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v=view;
        contactholder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.events_card, null);
            holder =new contactholder();

            TextView t = view.findViewById(R.id.name); holder.tx_1=t;
            t.setText(mainList.get(i).name);
            TextView t2 = view.findViewById(R.id.description); holder.tx_2=t2;
            t2.setText(mainList.get(i).description);
            TextView t3 = view.findViewById(R.id.venue); holder.tx_3=t3;
            t3.setText(mainList.get(i).venue);
            TextView t4 = view.findViewById(R.id.time); holder.tx_4=t4;
            t4.setText(mainList.get(i).time);
            TextView t5 = view.findViewById(R.id.date); holder.tx_5=t5;
            t5.setText(mainList.get(i).date);
            TextView t6 = view.findViewById(R.id.coord); holder.tx_6=t6;
            t6.setText(mainList.get(i).coord);
            TextView t7 = view.findViewById(R.id.contact); holder.tx_7=t7;
            t7.setText(mainList.get(i).contact);

            view.setTag(holder);
        }
        else
        {
            holder=(contactholder) view.getTag();
            holder.tx_1.setText(mainList.get(i).name);
            holder.tx_2.setText(mainList.get(i).description);
            holder.tx_3.setText(mainList.get(i).venue);
            holder.tx_4.setText(mainList.get(i).time);
            holder.tx_5.setText(mainList.get(i).date);
            holder.tx_6.setText(mainList.get(i).coord);
            holder.tx_7.setText(mainList.get(i).contact);


        }

        return view;
    }



    static class contactholder
    {
        TextView tx_1,tx_2,tx_3,tx_4,tx_5,tx_6,tx_7;
    }




    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String JSON_STRING;
        String indata;
        String json_url;

        JSONObject jsobject;
        JSONArray jsarray;
        protected void onPreExecute() {

            json_url="https://plasma2k19.000webhostapp.com/vitappapi/login/login/events.php";
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
            catch (Exception e){
            }

            return "empty";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            indata=result;
            if(indata.equals("empty"))
            {
                Toast.makeText(activity, "We are facing a server issue. Please try after some time.", Toast.LENGTH_LONG).show();
                activity.finish();
                return;
            }

            try {
                jsobject = new JSONObject(indata);
                JSONObject jo;


                jsarray = jsobject.getJSONArray("EVENT");
                length=jsarray.length();

                for(int i=0;i<length;i++)
                {

                    jo = jsarray.getJSONObject(i);
                    name= jo.getString("NAME");
                    description = jo.getString("DESCRIPTION");
                    venue="Venue : "+jo.getString("VENUE");
                    time = "Time : "+jo.getString("TIME");
                    date = "Date : "+jo.getString("DATE");
                    coord = "Faculty Co-ordinator : "+jo.getString("FACULTY_NAME");
                    contact = "Contact : "+jo.getString("CONTACT");
                    EventStore obj=new EventStore(name,description,venue,time,date,coord,contact);
                    mainList.add(obj);
                }
                notifyD();
                pbrobj.setVisibility(View.INVISIBLE);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


}
