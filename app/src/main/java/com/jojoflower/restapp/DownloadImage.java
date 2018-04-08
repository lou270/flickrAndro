package com.jojoflower.restapp;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by lou-l on 27/03/2018.
 */

class DownloadImage extends AsyncTask<URL, Integer, Bitmap> {

    protected Bitmap doInBackground(URL... urls) {

        return null;
    }

    protected void onPostExecute(Bitmap bitmap) {
    }
}
