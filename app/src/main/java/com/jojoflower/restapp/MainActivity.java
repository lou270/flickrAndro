package com.jojoflower.restapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        getDataFlickr("cats");
    }

    private void getDataFlickr(String tags) {
        DownloadJSONFlickr task = new DownloadJSONFlickr(MainActivity.this);
        tags = tags.replace(" ", "+");
        task.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags="+tags+"&format=json");
    }

    public void btnClick(View v) {
        EditText ev = findViewById(R.id.categorieText);
        getDataFlickr(ev.getText().toString());
    }
}
