package com.codepath.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.flixster.Models.Movie;
import com.codepath.flixster.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    Context context;
    List<Movie> movies;
    public static final String TAG = "MovieAdapter";

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    //Inflates XML file to a viewholder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        Log.d(TAG, "onCreateViewHolder");
        return new ViewHolder(movieView);
    }

    //Ppopulates data into the item through the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder "+ position);

        //Get movie at the passed in position
        Movie movie = movies.get(position);
        //Bind the movie data into the view holder
        holder.bind(movie);

    }

    //Returns total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ScrollView scrollable;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            //tvOverview.setMovementMethod(new ScrollingMovementMethod());
            ivPoster = itemView.findViewById(R.id.ivPoster);
            scrollable = itemView.findViewById(R.id.childScroll);

            tvOverview.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event){
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });
            tvOverview.setMovementMethod(new ScrollingMovementMethod());
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageURL;
           if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageURL = movie.getBackdropPath();
            }
           else {
               imageURL = movie.getPosterPath();
           }
            Glide.with(context).load(imageURL).into(ivPoster);
        }
    }






}
