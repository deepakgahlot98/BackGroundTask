package com.gahlot.backgroundtask101.ThreadPoolExecutor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class QuoteFetcher {

    private static QuoteFetcher instance = null;

    private String link = "https://api.kanye.rest/";
    private static final String TAG = "QuoteFetcher";

    private QuoteFetcher() {

    }

    public synchronized static QuoteFetcher getInstance() {
        if (instance == null) {
            instance = new QuoteFetcher();
        }
        return instance;
    }

    /**
     * Method to make json object request where json response starts wtih {
     * */
     public String makeRequest() throws IOException {
         String quote = "";
        URL url = new URL(link);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        String jsonString = sb.toString();
         JSONObject jsonObject = null;
         try {
             jsonObject = new JSONObject(jsonString);
             quote = jsonObject.getString("quote");
         } catch (JSONException e) {
             e.printStackTrace();
         }

        return quote;
    }

}
