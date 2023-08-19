package com.example.softeng306_application.Adaptor;

import android.content.Context;
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

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {
    Context context;
    MediaPlayer mediaPlayer;

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
        holder.categoryName.setText(categoryList.get(position).getCategoryType());
        playSound(categoryList.get(position), holder.cardView);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void playSound(Category category, View itemView){
        final String audio = category.getAudioFileName();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Operations to perform when the play ImageView is clicked
                int i = context.getResources().getIdentifier(
                        audio, "raw",
                        context.getPackageName());
                //Using MediaPlayer to play the audio file
                if (mediaPlayer != null)
                    mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(context, i);
                mediaPlayer.start();
            }
        });
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
