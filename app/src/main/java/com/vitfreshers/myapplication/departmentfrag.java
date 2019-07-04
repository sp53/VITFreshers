package com.vitfreshers.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class departmentfrag extends Fragment implements FragmentSwipeInterface{

    private ArrayList<Schedule> scheduleArrayList;
    private ListView listView;
    View vhold;
    dptScheduleData objec;

    public departmentfrag(dptScheduleData obj)
    {
        objec=obj;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.department_frag, container, false);
        vhold=view;

        scheduleArrayList = new ArrayList<>();
        // For Testing
        for (int i = 0; i < objec.length; i++) {
            scheduleArrayList.add(new Schedule(objec.name[i],objec.venue[i],objec.time[i],objec.date[i],objec.person[i]));
        }

        listView = vhold.findViewById(R.id.schedule_listview_department);
        MyListAdapter myListAdapter = new MyListAdapter(getActivity(), scheduleArrayList);
        listView.setAdapter(myListAdapter);


        return view;

    }


    @Override
    public void fragmentBecameVisible() {

        scheduleArrayList = new ArrayList<>();
        // For Testing
        for (int i = 0; i < objec.length; i++) {
            scheduleArrayList.add(new Schedule(objec.name[i],objec.venue[i],objec.time[i],objec.date[i],objec.person[i]));
        }

        listView = vhold.findViewById(R.id.schedule_listview_department);
        MyListAdapter myListAdapter = new MyListAdapter(getActivity(), scheduleArrayList);
        listView.setAdapter(myListAdapter);

    }
}