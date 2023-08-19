package com.example.softeng306_application.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.View.ListActivity;
import com.example.softeng306_application.ViewModel.MainViewModel;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {
    Context context;
    MediaPlayer mediaPlayer;
    MainViewModel mainViewModel;

    private List<Category> categoryList;

    public CategoryRecyclerAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CategoryRecyclerAdapter.CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        String audio = category.getAudioFileName();
        holder.categoryName.setText(categoryList.get(position).getCategoryType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCategoryClick(category, audio);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    private void onCategoryClick(Category category, String audio){
        playSound(audio);
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra("CATEGORY", category);
        context.startActivity(intent);
    }

    private void playSound(String audio){
        int i = context.getResources().getIdentifier(
                audio, "raw",
                context.getPackageName());
        //Using MediaPlayer to play the audio file
        if (mediaPlayer != null)
            mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(context, i);
        mediaPlayer.start();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        CardView cardView;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.txt_category);
            cardView = itemView.findViewById(R.id.cardview_category);
        }

    }
}
