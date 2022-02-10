package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.foodapp.databinding.ActivityFoodDataBinding;
import com.example.foodapp.model.Food;

public class FoodData extends AppCompatActivity {
    private ActivityFoodDataBinding binding;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(FoodData.this, R.color.my_color));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Food foodData = (Food) extras.getSerializable("food");
        binding.name.setSelected(true);
        binding.name.setText(foodData.getName());
        binding.ingredients.setText(foodData.getIngredients());
        binding.steps.setText(foodData.getSteps());


    }
}