package com.example.movieratings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    Context context;
    List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        Movie movie = movieList.get(position);
        holder.rating.setText(movie.getRating().toString());
        holder.title.setText(movie.getMovie_name());
        holder.overview.setText(movie.getOverview());
        String string_like = String.valueOf(movie.getLikes());
        holder.likes.setText("("+string_like+")");
        Glide.with(context).load(movie.getPoster()).into(holder.poster);
        Glide.with(context).load(movie.getPoster()).into(holder.posterBG);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Details.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",movie.getMovie_name());
                bundle.putString("overview",movie.getOverview());
                bundle.putInt("likes",movie.getLikes());
                bundle.putDouble("rating",movie.getRating());
                bundle.putString("poster",movie.getPoster());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        TextView title, overview, likes, rating;
        ImageView poster,posterBG;
        ConstraintLayout constraintLayout;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster_img);
            title = itemView.findViewById(R.id.title);
            likes = itemView.findViewById(R.id.likes);
            posterBG = itemView.findViewById(R.id.poster_bg);
            rating = itemView.findViewById(R.id.rating);
            overview = itemView.findViewById(R.id.overview);
            constraintLayout = itemView.findViewById(R.id.main_layout);

        }
    }
}
