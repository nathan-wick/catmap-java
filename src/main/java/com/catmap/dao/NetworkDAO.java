package com.catmap.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class NetworkDAO {

	public String request(String endpoint) throws Exception  {
		
		StringBuilder sb = new StringBuilder();
		URL url = new URL(endpoint);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		try {

			InputStream inputStream = urlConnection.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String inputLine = bufferedReader.readLine();

			while (inputLine != null) {

				sb.append(inputLine);
				inputLine = bufferedReader.readLine();

			}

		} finally {

			urlConnection.disconnect();

		}
		
		return sb.toString();
			
	}

}