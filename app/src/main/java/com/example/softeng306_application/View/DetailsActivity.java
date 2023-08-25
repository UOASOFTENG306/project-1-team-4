package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.softeng306_application.Adaptor.SliderAdapter;
import com.example.softeng306_application.Adaptor.ViewPageAdapter;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.DetailsViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;
import com.google.android.material.slider.Slider;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class DetailsActivity extends AppCompatActivity implements Activity {

    private DetailsViewModel detailsViewModel;

    int[] images;

    private class ViewHolder {

        SliderView sliderView;
        TabLayout tabLayout;
        ViewPager2 viewPager2;
        ViewPageAdapter viewPageAdapter;
        TextView priceText, nameText;
        ImageView logoImage;
        ImageButton favouriteButton, backButton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ViewHolder vh = new ViewHolder();
        vh.tabLayout = findViewById(R.id.tabLayout);
        vh.viewPager2 = findViewById(R.id.viewPager_tab_content);

        vh.priceText = findViewById(R.id.txt_detail_price);
        YoYo.with(Techniques.FadeInUp).duration(300).playOn(vh.priceText);

        vh.nameText = findViewById(R.id.txt_detail_name);
        YoYo.with(Techniques.ZoomIn).duration(300).playOn(vh.nameText);

        vh.logoImage = findViewById(R.id.img_detail_logo);
        YoYo.with(Techniques.StandUp).duration(700).playOn(vh.logoImage);

        vh.favouriteButton = findViewById(R.id.btn_detail_favourite);
        YoYo.with(Techniques.Bounce).duration(200).playOn(vh.favouriteButton);

        vh.backButton = findViewById(R.id.btn_back);

        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        detailsViewModel.getFavouriteRestaurants();
        detailsViewModel.isFavourite().observe(this, isFavourite -> {
            int heartType;

            if(isFavourite){
                heartType = R.drawable.heart_fav;
                detailsViewModel.addFavourite();
            } else {
                heartType = R.drawable.heart;
                detailsViewModel.removeFavourite();
            }
            vh.favouriteButton.setImageResource(heartType);
        });

        vh.favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsViewModel.setOppositeFavourite();
            }
        });


        vh.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            if(intent.hasExtra("RESTAURANT")){
                Restaurant restaurant = intent.getParcelableExtra("RESTAURANT");
                detailsViewModel.setRestaurant(restaurant);
                vh.nameText.setText(restaurant.getName());
                vh.priceText.setText(restaurant.getPrice());
                vh.logoImage.setImageResource(showImage(restaurant));
                vh.viewPageAdapter = new ViewPageAdapter(this);
            }
        }

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

        vh.sliderView = findViewById(R.id.imageSlider);

        images = detailsViewModel.getBackgroundImages(this);
        SliderAdapter sliderAdapter = new SliderAdapter(images);

        vh.sliderView.setSliderAdapter(sliderAdapter);
        vh.sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        vh.sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        vh.sliderView.startAutoCycle();

    }

    private int showImage(Restaurant restaurant) {
        int i = this.getResources().getIdentifier(restaurant.getLogoImage(), "drawable", this.getPackageName());
        return i;
    }
}
