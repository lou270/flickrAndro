package com.jojoflower.restapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFlickr();
    }

    public void getDataFlickr() {
        DownloadJSONFlickr task = new DownloadJSONFlickr(MainActivity.this);
        EditText et = findViewById(R.id.categorieText);
        String tagURL = (et.getText().toString()).replace(" ", "+");

        task.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags="+tagURL+"&format=json");
    }
}
