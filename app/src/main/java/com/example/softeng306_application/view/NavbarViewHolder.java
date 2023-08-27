package com.example.softeng306_application.view;

import android.view.View;
import android.widget.ImageButton;

import com.example.softeng306_application.R;
import com.example.softeng306_application.viewmodel.MainViewModel;

public class NavbarViewHolder {
    protected ImageButton favouritesNavButton, listNavButton, mainNavButton, searchNavButton, logoutNavButton;

    protected MainViewModel mainViewModel;

    public NavbarViewHolder(View v, MainViewModel mainViewModel) {
        favouritesNavButton = v.findViewById(R.id.btn_favourites);
        listNavButton = v.findViewById(R.id.btn_list);
        mainNavButton = v.findViewById(R.id.btn_main);
        searchNavButton = v.findViewById(R.id.btn_search);
        logoutNavButton = v.findViewById(R.id.btn_logout);
        this.mainViewModel = mainViewModel;
    }

}
