/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.app.LoaderManager.LoaderCallbacks;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {

    public static final String TAG = EarthquakeActivity.class.getName();
    private static final String SAMPLE_JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4&limit=50";
    private static final int EARTHQUAKE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        LoaderManager loaderManager = getLoaderManager();
        Log.d(TAG, "onCreate: Executes initLoader");
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: executes create loader");
        return new EarthquakeLoader(this,SAMPLE_JSON_RESPONSE);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        Log.d(TAG, "onLoadFinished: executes load finished");
        updateUi((ArrayList<Earthquake>) data);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d(TAG, "onLoaderReset: ");
        updateUi(new ArrayList<Earthquake>());
    }

    private void updateUi(final ArrayList<Earthquake> earthquakes){
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthQuakeListAdapter adapter = new EarthQuakeListAdapter(this,android.R.layout.simple_list_item_1,earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        assert earthquakeListView != null;
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = earthquakes.get(position).getUrl();
                Intent browser_intent = new Intent(Intent.ACTION_VIEW);
                browser_intent.setData(Uri.parse(url));
                startActivity(browser_intent);
            }
        });
    }
}
