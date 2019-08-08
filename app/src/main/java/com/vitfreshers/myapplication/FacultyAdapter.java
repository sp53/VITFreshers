package com.vitfreshers.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

public class FacultyAdapter extends BaseAdapter {
    private Activity activity;

    private LayoutInflater layoutInflater;
     ProgressBar pbrobj;

    String name[];
    String id[];
    String email[];
    String intercom[];
    String venue[];
    String school[];



    int length=0;

    public FacultyAdapter(Activity activity, ProgressBar obj) {
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
            view = layoutInflater.inflate(R.layout.faculty_card, null);
            holder =new contactholder();

            TextView t = view.findViewById(R.id.name); holder.tx_1=t;
            t.setText(name[i]);
            TextView t2 = view.findViewById(R.id.empid); holder.tx_2=t2;
            t2.setText(id[i]);
            TextView t3 = view.findViewById(R.id.email); holder.tx_3=t3;
            t3.setText(email[i]);
            TextView t4 = view.findViewById(R.id.intercom); holder.tx_4=t4;
            t4.setText(intercom[i]);
            TextView t5 = view.findViewById(R.id.school); holder.tx_5=t5;
            t5.setText(school[i]);
            TextView t6 = view.findViewById(R.id.venue); holder.tx_6=t6;
            t6.setText(venue[i]);

            view.setTag(holder);
        }
        else
        {
            holder=(contactholder) view.getTag();
            holder.tx_1.setText(name[i]);
            holder.tx_2.setText(id[i]);
            holder.tx_3.setText(email[i]);
            holder.tx_4.setText(intercom[i]);
            holder.tx_5.setText(school[i]);
            holder.tx_6.setText(venue[i]);

        }

        return view;
    }


    static class contactholder
    {
        TextView tx_1,tx_2,tx_3,tx_4,tx_5,tx_6;
    }




    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String JSON_STRING;
        String indata;
        String json_url;

        JSONObject jsobject;
        JSONArray jsarray;
        protected void onPreExecute() {

            json_url="http://vitappapi.herokuapp.com/faculty_details/faculty.php";
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
            if(indata.equals("e"))
            {
                return;
            }

            try {
                jsobject = new JSONObject(indata);
                JSONObject jo;


                jsarray = jsobject.getJSONArray("Faculty");
                length=jsarray.length();
                name=new String[length];
                id=new String[length];
                email=new String[length];
                venue=new String[length];
                intercom=new String[length];
                school=new String[length];

                for(int i=0;i<length;i++)
                {
                    jo = jsarray.getJSONObject(i);
                    name[i]= "Name : "+jo.getString("Name ");
                    id[i] = "Employee ID : "+jo.getString("ID");
                    school[i]="School : "+jo.getString("School");
                    venue[i] = "Room : "+jo.getString("Venue");
                    email[i] = "Email ID : "+jo.getString("Email");
                    intercom[i] = "Intercom : "+jo.getString("Intercol");
                }
                notifyD();
                pbrobj.setVisibility(View.INVISIBLE);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


}
