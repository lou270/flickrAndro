package com.jojoflower.restapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by lou-l on 08/04/2018.
 */

public class DownloadJSONFlickr extends AsyncTask<String, Integer, JSONObject> {

    private AppCompatActivity _mainActivity;

    public DownloadJSONFlickr(AppCompatActivity mainActivity) {
        _mainActivity = mainActivity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url = null;
        InputStream in = null;
        HttpURLConnection urlConnection = null;
        String rawJson = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());


            byte[] content = new byte[1024];
            int bytesRead = 0;
            while((bytesRead = in.read(content)) != -1) {
                rawJson += new String(content, 0, bytesRead);
            }

            rawJson = rawJson.substring("jsonFlickrFeed(".length(), rawJson.length()-1);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(rawJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    protected void onPostExecute(JSONObject json) {
        ListView lv = (ListView)_mainActivity.findViewById(R.id.listViewImage);
        ImageAdapter tab = new ImageAdapter(lv.getContext(), new ArrayList<MyImage>());
        lv.setAdapter(tab);

        try {
            JSONArray jsonItems = json.getJSONArray("items");
            for(int i = 0; i < jsonItems.length(); i++){
                JSONObject item = jsonItems.getJSONObject(i);
                tab.add(new MyImage(item.getString("title"), item.getJSONObject("media").getString("m")));
            }
            tab.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
