package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Schedule> arrayList;
    private LayoutInflater layoutInflater;

    public MyListAdapter(Activity activity, ArrayList<Schedule> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_schedule_layout, null);
            Schedule obj=(Schedule)getItem(i);
            TextView t = view.findViewById(R.id.program_name);
            t.setText(obj.getProgram_name());
            TextView t2 = view.findViewById(R.id.timing);
            t2.setText(obj.getTiming());
            TextView t3 = view.findViewById(R.id.venue_name);
            t3.setText(obj.getVenue());
            TextView t4 = view.findViewById(R.id.date);
            t4.setText(obj.getDate());
        }

        return view;
    }
}
