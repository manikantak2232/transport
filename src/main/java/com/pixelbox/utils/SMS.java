package com.pixelbox.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
 
public class SMS {
	public String sendSms(String userName, String apiHash, String messageToSend, 
												String senderName, String recipientNumbers) {
		try {
			// Construct data
			/*String user = "username=" + "youremail@address.com";
			String hash = "&hash=" + "Your API hash";
			String message = "&message=" + "This is your message";
			String sender = "&sender=" + "Hoop";
			String numbers = "&numbers=" + "918123456789";*/
			
			String user = "username=" + userName;
			String hash = "&hash=" + apiHash;
			String message = "&message=" + messageToSend;
			String sender = "&sender=" + senderName;
			String numbers = "&numbers=" + recipientNumbers;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/send/?").openConnection();
			String data = user + hash + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}
