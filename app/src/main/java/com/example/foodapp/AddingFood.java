package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.foodapp.databinding.ActivityAddingBinding;
import com.example.foodapp.model.Food;
import com.example.foodapp.utils.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddingFood extends AppCompatActivity {
    private ActivityAddingBinding binding;
    private MySharedPreference mySharedPreference;
    private Gson gson;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(AddingFood.this, R.color.my_color));
        mySharedPreference = MySharedPreference.getInstance(this);
        gson = new Gson();

        binding.add.setOnClickListener(view -> {
            String name = binding.name.getText().toString().trim();
            String ingredient = binding.ingredient.getText().toString().trim();
            String steps = binding.steps.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(AddingFood.this, "Taom nomini kiriting", Toast.LENGTH_SHORT).show();
            } else if (ingredient.isEmpty()) {
                Toast.makeText(AddingFood.this, "Kerakli mahsulotlarni kiriting", Toast.LENGTH_SHORT).show();
            } else if (steps.isEmpty()) {
                Toast.makeText(AddingFood.this, "Taom tayyorlanish tartibini kiriting kiriting", Toast.LENGTH_SHORT).show();
            } else {
                List<Food> list;
                Food food = new Food(name, ingredient, steps);
                String foodList = mySharedPreference.getFoodList();
                if (foodList.isEmpty()) {
                    list = new ArrayList<>();
                } else {
                    Type type = new TypeToken<List<Food>>() {
                    }.getType();
                    list = gson.fromJson(foodList, type);
                }
                list.add(food);
                String s = gson.toJson(list);
                mySharedPreference.setFoodList(s);
                finish();
            }
        });
    }
}