package com.example.foodapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private static MySharedPreference mySharedPreference = new MySharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MySharedPreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("myFile", Context.MODE_PRIVATE);
        }
        return mySharedPreference;
    }

    public String getFoodList() {
        return sharedPreferences.getString("foodList", "");
    }

    public void setFoodList(String foodList) {
        editor = sharedPreferences.edit();
        editor.putString("foodList", foodList);
        editor.commit();
    }

    public void clearCache() {
        editor.clear();
    }
}