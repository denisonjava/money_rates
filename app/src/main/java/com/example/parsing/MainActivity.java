package com.example.parsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     final DownloadData downloadData = new DownloadData();
        downloadData.execute("http://www.cbr.ru/scripts/XML_daily.asp");

    }
    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            int i=1;
            //Log.d(TAG, "xml document: " + s);
            ValuteXMLParser parser = new ValuteXMLParser();
            if(s!=null && parser.parse(s))
            {
                for(Valute prod: parser.getValutes()){
                    //Log.d("XML", prod.toString());
                    System.out.println(i + " - " + prod.toString());
                    i++;
                }
            }
            final Button butTest = (Button)findViewById(R.id.button);
            final TextView tvInfo = (TextView)findViewById(R.id.textView);

            butTest.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    tvInfo.setText(toString());
                }
            });
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try{
                content = downloadXML(strings[0]);
            }
            catch (IOException e){
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
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line=null;
                while ((line=reader.readLine()) != null) {
                    xmlResult.append(line);
                }
                return xmlResult.toString();
            } catch(MalformedURLException e) {
                Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage());
            } catch(IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            } catch(SecurityException e) {
                Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.getMessage());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
            return null;
        }
    }
}
