package com.example.softeng306_application.View;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.R;

public class Navbar {

    public static void setUpNavbar(NavbarViewHolder vh, Context context) {
        clickFavourites(vh, context);
        clickList(vh, context);
        clickMain(vh, context);
        clickSearch(vh, context);
        clickLogout(vh, context);
    }

    private static void clickFavourites(NavbarViewHolder vh, Context context) {
        vh.favouritesNavButton.setOnClickListener(v -> {
            Intent listIntent = new Intent(context, ListActivity.class);
            listIntent.putExtra("FAVOURITES", true);
            context.startActivity(listIntent);
        });
    }

    private static void clickList(NavbarViewHolder vh, Context context) {
        vh.listNavButton.setOnClickListener(v -> {
            Intent listIntent = new Intent(context, ListActivity.class);
            listIntent.putExtra("CATEGORY", new FastFood());
            context.startActivity(listIntent);
        });
    }

    private static void clickMain(NavbarViewHolder vh, Context context) {
        vh.mainNavButton.setOnClickListener(v -> {
            if(context.equals(R.layout.activity_main)) {
                Toast.makeText(context, "Already on main menu", Toast.LENGTH_SHORT).show();
            } else {
                Intent mainIntent = new Intent(context, MainActivity.class);
                context.startActivity(mainIntent);
            }
        });
    }

    private static void clickSearch(NavbarViewHolder vh, Context context) {
        vh.searchNavButton.setOnClickListener(v -> {
            Intent listIntent = new Intent(context, ListActivity.class);
            listIntent.putExtra("SEARCH", true);
            context.startActivity(listIntent);
        });
    }
    private static void clickLogout(NavbarViewHolder vh, Context context) {
        vh.logoutNavButton.setOnClickListener(v -> {
            vh.mainViewModel.logout();
            Intent loginIntent = new Intent(context, LoginActivity.class);
            context.startActivity(loginIntent);
        });
    }
}
