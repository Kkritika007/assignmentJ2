package com.example.a2java;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtility {

    public static ApiResponse getDataFromFile(String filename){
        // Your existing method to read data from a JSON file
        return null;
    }

    public static ApiResponse getDataFromApi(String apiUrl) {
        ApiResponse apiResponse = null;
        StringBuilder response = new StringBuilder();

        try {
            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Get input stream from the connection
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            // Read response line by line
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Close reader and disconnect connection
            reader.close();
            connection.disconnect();

            // Parse the JSON response into ApiResponse object
            Gson gson = new Gson();
            apiResponse = gson.fromJson(response.toString(), ApiResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiResponse;
    }
}
