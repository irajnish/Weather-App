package com.example.weatherapiapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton instance; //the variable instance checks if there has been existence of same module in memory
    private RequestQueue requestQueue;
    private static Context ctx;

    //Constructor sets up ability for only one class to exist
    private MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue(); //creates one version of request queue
    }
    //classic singleton setup
    public static synchronized MySingleton getInstance(Context context) {
        //if instance is null it hasn't been instantiated yet
        //so we make new instance
        if (instance == null) {
            instance = new MySingleton(context);
        }
        //else we return existing instance
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
