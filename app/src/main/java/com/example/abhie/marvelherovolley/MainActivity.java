package com.example.abhie.marvelherovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";


    ListView heroList;
    Button btnGetJson;
    ArrayList<Hero> arrayList;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heroList = findViewById(R.id.listView);
        btnGetJson = findViewById(R.id.button);
        progressBar = findViewById(R.id.progreeBar);
        arrayList = new ArrayList<Hero>();


        progressBar.setVisibility(View.INVISIBLE);
        btnGetJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                loadHeroList();
            }
        });



    }

    private void loadHeroList()
    {
        progressBar.setVisibility(View.VISIBLE);

        //Creating a string Request to send Send Request To the URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        btnGetJson.setEnabled(false);
                        Log.i("MainActivit_response", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("heroes");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                Hero hero = new Hero(jsonObject1.getString("name"),
                                        jsonObject1.getString("imageurl"));

                                arrayList.add(hero);
                                Log.i("arrayListItem", arrayList.toString());
                            }
                            Log.i("MainActivity_arrayList", arrayList.toString());
                            ListViewAdapter listViewAdapter = new ListViewAdapter(arrayList,
                                    getApplicationContext());
                            //adding the adapter to list view
                            heroList.setAdapter(listViewAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT)
                                .show();

                    }
                });
        // Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
