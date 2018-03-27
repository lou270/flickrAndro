package com.jojoflower.restapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageAdapter imageAdapter;
    ArrayList<MyImage> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getDataFlickr() {
        URL url = null;
        try {
            url = new URL("http://www.flickr.com/services/feeds/photos_public.gne?tags=cats&format=json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        DownloadFilesTask task = new DownloadFilesTask();
        task.execute(url, 0, new ArrayList());
    }

    private void fillListView() {
        ListView lv = findViewById(R.id.listViewMain);
        getDataFlickr();
        imageAdapter = new ImageAdapter(getApplicationContext(), images);
        lv.setAdapter(imageAdapter);
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, ArrayList> {

        protected ArrayList doInBackground(URL... urls) {
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream in = null;
            String jsonString = null;
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
                jsonString = readStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            jsonString = jsonString.substring(15, -1);
            JSONObject json = null;
            try {
                json = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ArrayList<String> items = null;
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

        private String readStream(InputStream is) {
            try {
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                int i = is.read();
                while(i != -1) {
                    bo.write(i);
                    i = is.read();
                }
                return bo.toString();
            } catch (IOException e) {
                return "";
            }
        }
    }


}
