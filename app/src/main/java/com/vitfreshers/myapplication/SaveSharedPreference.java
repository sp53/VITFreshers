package com.vitfreshers.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveSharedPreference
{
    static SharedPreferences shp;
    static SharedPreferences.Editor ed;

    public static void init(Context ctx)
    {
        shp = ctx.getSharedPreferences("VITFreshers_APP",ctx.MODE_PRIVATE);
        ed = shp.edit();
    }

    public static void setField(String key,String value)
    {
        ed.putString(key, value);
        ed.commit();
    }

    public static String getField(String s)
    {
        return shp.getString(s,null);
    }

    public static void logout()
    {
        ed.remove("CurrentUser");
        ed.remove("CurrentUserProcName");
        ed.remove("CurrentUserProcRoom");
        ed.remove("CurrentUserDep");
        ed.clear();
        ed.commit();
    }
}