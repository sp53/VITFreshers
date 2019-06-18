package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
    static SharedPreferences p;
    static SharedPreferences.Editor ed;

    public static void init(Context ctx)
    {
        p = ctx.getSharedPreferences("VITFreshers_APP",ctx.MODE_PRIVATE);
        ed = p.edit();
    }

    public static void setUserName(String userName)
    {
        ed.putString("Current", userName);
        ed.commit();
    }

    public static String getUserName()
    {
        return p.getString("Current",null);
    }

    public static void logout()
    {
        ed.remove("Current");
        ed.clear();
        ed.commit();
    }
}