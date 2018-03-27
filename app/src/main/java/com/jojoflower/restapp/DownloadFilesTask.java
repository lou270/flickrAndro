package com.jojoflower.restapp;

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

class DownloadFilesTask extends AsyncTask<URL, Integer, ArrayList> {

    protected ArrayList doInBackground(URL... urls) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) urls[0].openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(in.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList items = null;
        try {
            items = new json.get("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    protected void onPostExecute(ArrayList items) {
        int numberImage = items.size();
        for (int i = 0; i < numberImage; i++) {
            images.add(new MyImage(items.get(i).toString(), items.getString()));
        }
    }
}
