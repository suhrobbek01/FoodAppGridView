package com.example.foodapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.foodapp.databinding.ItemFoodBinding;
import com.example.foodapp.model.Food;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private List<Food> foodList;
    private OnItemClickListener onItemClickListener;

    public FoodAdapter(List<Food> foodList, OnItemClickListener onItemClickListener) {
        this.foodList = foodList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemFoodBinding binding;
        if (view == null) {
            binding = ItemFoodBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        } else {
            binding = ItemFoodBinding.bind(view);
        }
        binding.name.setText(foodList.get(i).getName());
        binding.name.setSelected(true);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onFoodItemClickListener(foodList.get(i));
            }
        });
        return binding.getRoot();
    }

    public interface OnItemClickListener {
        void onFoodItemClickListener(Food food);
    }
}