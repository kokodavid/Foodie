package com.koko.foodie;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {


    private static SharedPreferences sharedPref;




    public void initSharedPref(Context context) {
        if (sharedPref == null){
            sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        }

    }

    public  String readStr(String key, String value) {
        return sharedPref.getString(key, value);
    }

    public  void writeStr (String key, String value) {
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString(key, value);
        prefEditor.commit();
    }

    public int readInt(String key, int value) {
        return sharedPref.getInt(key, value);
    }

    public void writeInt (String key, int value) {
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt(key, value);
        prefEditor.commit();
    }


}