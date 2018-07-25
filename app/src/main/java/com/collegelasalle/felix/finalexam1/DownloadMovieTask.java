package com.collegelasalle.felix.finalexam1;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadMovieTask extends AsyncTask<String, Void, String> {

    private StorageFragment storageFragment;

    public DownloadMovieTask(StorageFragment storageFragment) {
        this.storageFragment = storageFragment;
    }

    @Override
    protected String doInBackground(String... strings) {
        String json = "";

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(strings[0]).openConnection();
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            connection.disconnect();
            json = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    protected void onPostExecute(String result) {
        storageFragment.parseJson(result);
    }
}
