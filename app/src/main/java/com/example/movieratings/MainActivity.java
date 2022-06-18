package com.example.movieratings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        fetchMovies();
        movieList = new ArrayList<>();


    }

    private void fetchMovies() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=b2dc603ca5e9bfee07733dca3c702258";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0 ; i < jsonArray.length(); i++ ){
                        JSONObject jsonObject =jsonArray.getJSONObject(i);
                        String imageURL = jsonObject.getString("poster_path");
                        String imageURLcut = imageURL.substring(1);
                        String posterURL = "https://www.themoviedb.org/t/p/original/"+imageURLcut;
                        String title = jsonObject.getString("title");
                        String overview = jsonObject.getString("overview");
                        Double rating = jsonObject.getDouble("vote_average");
                        int likes = jsonObject.getInt("vote_count");

                        Movie movie = new Movie(title,overview,rating,posterURL,likes);
                        movieList.add(movie);
                    }

                    MovieAdapter adapter = new MovieAdapter(MainActivity.this,movieList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}