package com.jojoflower.restapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lou-l on 27/03/2018.
 */

class DownloadImage extends AsyncTask<String, Integer, Bitmap> {

    MyImage _img;
    ImageAdapter _tab;

    public DownloadImage(MyImage img, ImageAdapter tab) {
        _img = img;
        _tab = tab;
    }

    protected Bitmap doInBackground(String... strings) {
        URL url = null;
        InputStream in = null;
        HttpURLConnection urlConnection = null;
        Bitmap bitmap = null;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());

            bitmap = BitmapFactory.decodeStream(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return bitmap;
    }

    protected void onPostExecute(Bitmap bitmap) {
        _img.setBitmap(bitmap);
        _tab.notifyDataSetChanged();
    }
}
