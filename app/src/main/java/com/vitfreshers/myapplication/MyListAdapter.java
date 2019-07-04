package com.vitfreshers.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

        View v=view;
        contactholder holder;
        Schedule obj=(Schedule)getItem(i);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_schedule_layout, null);
            holder =new contactholder();

            TextView t = view.findViewById(R.id.program_name); holder.tx_1=t;
            t.setText(obj.getProgram_name());
            TextView t2 = view.findViewById(R.id.timing); holder.tx_2=t2;
            t2.setText(obj.getTiming());
            TextView t3 = view.findViewById(R.id.venue_name); holder.tx_3=t3;
            t3.setText(obj.getVenue());
            TextView t4 = view.findViewById(R.id.date); holder.tx_4=t4;
            t4.setText(obj.getDate());
            TextView t5 = view.findViewById(R.id.resper); holder.tx_5=t5;
            t5.setText(obj.getPerson());

            view.setTag(holder);
        }
        else
        {
            holder=(contactholder) view.getTag();
            holder.tx_1.setText(obj.getProgram_name());
            holder.tx_2.setText(obj.getTiming());
            holder.tx_3.setText(obj.getVenue());
            holder.tx_4.setText(obj.getDate());
            holder.tx_5.setText(obj.getPerson());

        }

        return view;
    }

    static class contactholder
    {
        TextView tx_1,tx_2,tx_3,tx_4,tx_5;
    }
}


