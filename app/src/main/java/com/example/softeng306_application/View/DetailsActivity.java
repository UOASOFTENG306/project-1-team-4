package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.softeng306_application.Adaptor.ViewPageAdapter;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.DetailsViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;
import com.google.android.material.tabs.TabLayout;

public class DetailsActivity extends AppCompatActivity implements Activity {

    private DetailsViewModel detailsViewModel;
    private class ViewHolder {
        TabLayout tabLayout;
        ViewPager2 viewPager2;
        ViewPageAdapter viewPageAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ViewHolder vh = new ViewHolder();
        this.detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        vh.tabLayout = findViewById(R.id.tabLayout);
        vh.viewPager2 = findViewById(R.id.viewPager_tab_content);
        vh.viewPageAdapter = new ViewPageAdapter(this);
        vh.viewPager2.setAdapter(vh.viewPageAdapter);
        vh.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vh.viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vh.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                vh.tabLayout.getTabAt(position).select();
            }
        });
        
    }
}