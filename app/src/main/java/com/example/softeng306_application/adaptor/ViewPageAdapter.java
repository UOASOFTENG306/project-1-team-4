package com.example.softeng306_application.adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.view.DescriptionFragment;
import com.example.softeng306_application.view.ReviewFragment;

public class ViewPageAdapter extends FragmentStateAdapter
{
    private Restaurant restaurant;
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new DescriptionFragment();
            case 1: return new ReviewFragment();
            default: return new ReviewFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
