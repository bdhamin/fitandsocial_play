package utils.GCM;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mint on 6-10-14.
 */
public class SendToGCM {

    public static void post(String apiKey, GCMContent content){

        try{

            URL url = new URL("https://android.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //Set the headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key="+apiKey);


            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

            Gson gson = new Gson();
            String json = gson.toJson(content);
            wr.writeBytes(json);

            //Send the request
            wr.flush();

            wr.close();

            //Get the response
            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 7. Print result
            System.out.println(response.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}