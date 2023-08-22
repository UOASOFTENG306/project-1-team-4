package com.example.softeng306_application.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.R;

import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ReviewViewHolder> {

    Context context;
    List<Review> reviewList;

    public ReviewRecyclerAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewRecyclerAdapter.ReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.review_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        // TODO: refactor to pull review data from database
        holder.username.setText(reviewList.get(position).getUserID());
        holder.comment.setText(reviewList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{

        TextView username, comment;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txt_username);
            comment = itemView.findViewById(R.id.txt_review_comment);
        }

    }
}
