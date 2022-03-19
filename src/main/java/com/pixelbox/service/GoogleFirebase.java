package com.pixelbox.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//import com.pixelbox.bl.OwnCardsBL;

@Path("/firebase")
public class GoogleFirebase {
	@POST
	@Path("/pushnotification")
	@Produces(MediaType.APPLICATION_JSON)
	public void sendPushNotification(HashMap<String, Object> requestParameters){		
		String token = requestParameters.get("token").toString();
		
		try {
	        HttpURLConnection httpcon = (HttpURLConnection) ((new URL("https://fcm.googleapis.com/fcm/send").openConnection()));
	        httpcon.setDoOutput(true);
	        httpcon.setRequestProperty("Content-Type", "application/json");
	        httpcon.setRequestProperty("Authorization", "key=AIzaSyATqM6pkj41kDJQUG5iOOZqcLb4RuuVFvc");
	        httpcon.setRequestMethod("POST");
	        httpcon.connect();
	        System.out.println("Connected!");

	        byte[] outputBytes = "{\"notification\":{\"title\": \"My title\", \"text\": \"My text\", \"sound\": \"default\"}, \"to\": \"cAhmJfN...bNau9z\"}".getBytes("UTF-8");
	        OutputStream os = httpcon.getOutputStream();
	        os.write(outputBytes);
	        os.close();

	        // Reading response
	        InputStream input = httpcon.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	            for (String line; (line = reader.readLine()) != null;) {
	                System.out.println(line);
	            }
	            System.out.println("Http POST request sent!");	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
}
