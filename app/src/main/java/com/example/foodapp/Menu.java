package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.foodapp.adapter.FoodAdapter;
import com.example.foodapp.databinding.ActivityMenuBinding;
import com.example.foodapp.model.Food;
import com.example.foodapp.utils.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class
Menu extends AppCompatActivity {

    private ActivityMenuBinding binding;
    private FoodAdapter adapter;
    private MySharedPreference mySharedPreference;
    private Gson gson;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(Menu.this, R.color.my_color));

        mySharedPreference = MySharedPreference.getInstance(this);
        gson = new Gson();
        List<Food> list = getList();
        adapter = new FoodAdapter(list, food -> {
            Intent intent = new Intent(Menu.this, FoodData.class);
            intent.putExtra("food", food);
            startActivity(intent);
        });
        binding.listView.setAdapter(adapter);
    }

    private List<Food> getList() {
        List<Food> foodList;
        String foodListString = mySharedPreference.getFoodList();
        if (foodListString.isEmpty()) {
            foodList = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<Food>>() {
            }.getType();
            foodList = new ArrayList(gson.fromJson(foodListString, type));
        }
        return foodList;
    }
}