package com.example.parsing;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class  DownloadData extends AsyncTask<String, Void, String> {
    private static final String TAG = "DownloadData";

    @Override
    protected String doInBackground(String... strings) {
        String content = null;
        try {
            content = downloadXML(strings[0]);
        } catch (IOException e) {
            Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
        }
        return content;
    }

    private String downloadXML(String urlPath) throws IOException {
        StringBuilder xmlResult = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "windows-1251"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                xmlResult.append(line);
            }
            return xmlResult.toString();
        } catch (MalformedURLException e) {
            Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
        } catch (SecurityException e) {
            Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }
}