package com.example.tmdbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        final String url7 = "https://desafio-mobile.nyc3.digitaloceanspaces.com/movies";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                JsonArrayRequest jsonArrayRequest = new
                        JsonArrayRequest(Request.Method.GET,url7,
                        new JSONArray(),
                        new com.android.volley.Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    for (int i = 0; i < response.length(); ++i) {
                                        JSONObject rec = response.getJSONObject(i);
                                        String id = rec.getString("title");
                                        textView.append(id);
                                    }
                                    //String server_data = response.toString();
                                    //JSONObject object = new JSONObject(server_data);
                                    //textView.setText(object.getString("name"));
                                    //textView.setText(server_data);
                                    //Log.e("data",server_data);
                                    //System.out.println(object);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error8", error.toString());
                    }
                });

                queue.add(jsonArrayRequest);

            }
        });
    }
}