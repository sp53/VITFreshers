package com.vitfreshers.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ScheduleActivity extends AppCompatActivity {
    SectionPageAdapter adapter;
    SectionPageAdapter spa;
    ViewPager mviewpager;
    dptScheduleData objd;
    dptScheduleData objc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        objd=new dptScheduleData();
        objd.fetchData(1);

        objc=new dptScheduleData();
        objc.fetchData(0);

        Toolbar toolbar = findViewById(R.id.toolbar_schedule_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("VIT Freshers");


        adapter=new SectionPageAdapter(getSupportFragmentManager());
        spa=new SectionPageAdapter(getSupportFragmentManager());
        mviewpager=(ViewPager) findViewById(R.id.container);

        mviewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                FragmentSwipeInterface fragment = (FragmentSwipeInterface) adapter.instantiateItem(mviewpager, i);
                if (fragment != null) {
                    fragment.fragmentBecameVisible();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });

        setupViewPager(mviewpager);
        TabLayout tbl=(TabLayout) findViewById(R.id.tabs);
        tbl.setupWithViewPager(mviewpager);
    }

    private void setupViewPager(ViewPager vp)
    {
        adapter.addFragment(new collegefrag(objc),"College");
        adapter.addFragment(new departmentfrag(objd),"Department");
        vp.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
