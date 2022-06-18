package com.example.movieratings;

public class Movie {
    private String movie_name, overview;
    private Double rating;
    private String poster;
    private int likes;

    public String getMovie_name() {
        return movie_name;
    }

    public String getOverview() {
        return overview;
    }

    public Double getRating() {
        return rating;
    }

    public String getPoster() {
        return poster;
    }

    public int getLikes() {
        return likes;
    }

    public Movie(String movie_name, String overview, Double rating, String poster, int likes) {
        this.movie_name = movie_name;
        this.overview = overview;
        this.rating = rating;
        this.poster = poster;
        this.likes = likes;
    }
}
