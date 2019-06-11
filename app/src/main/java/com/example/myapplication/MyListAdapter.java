package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        }

        return view;
    }
}
