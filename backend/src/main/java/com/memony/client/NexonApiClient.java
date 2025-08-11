package com.memony.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Component
public class NexonApiClient {

    public String getCharacters(String nexonApiKey) {
        String result = "";

        try {
            URL url = new URL("https://open.api.nexon.com/maplestory/v1/character/list");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", nexonApiKey);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            result = response.toString();
        } catch (Exception exception) {
            log.error("e: ", exception);
            result = "ERROR";
        }

        return result;
    }

}
