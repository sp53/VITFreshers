package com.vitfreshers.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveSharedPreference
{
    static SharedPreferences p;
    static SharedPreferences.Editor ed;

    public static void init(Context ctx)
    {
        p = ctx.getSharedPreferences("VITFreshers_APP",ctx.MODE_PRIVATE);
        ed = p.edit();
    }

    public static void setField(String key,String value)
    {
        ed.putString(key, value);
        ed.commit();
    }

    public static String getField(String s)
    {
        return p.getString(s,null);
    }

    public static void logout()
    {
        ed.remove("CurrentUser");
        ed.remove("CurrentUserProcName");
        ed.remove("CurrentUserProcRoom");
        ed.clear();
        ed.commit();
    }
}