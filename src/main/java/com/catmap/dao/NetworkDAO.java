package com.catmap.dao;

import javax.net.ssl.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import org.springframework.stereotype.Component;

@Component
public class NetworkDAO {

    // Send a request to a given end point and read the response.
    public String request(String endpoint) throws Exception  {

        StringBuilder sb = new StringBuilder();
        URL url = new URL(endpoint);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        if (urlConnection instanceof HttpsURLConnection) {

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] chain, String authType) {}

                public void checkServerTrusted(X509Certificate[] chain, String authType) {}

                public X509Certificate[] getAcceptedIssuers() {

                    return new X509Certificate[0];

                }

            }}, new SecureRandom());

            HttpsURLConnection httpsConnection = (HttpsURLConnection) urlConnection;
            httpsConnection.setSSLSocketFactory(sslContext.getSocketFactory());
            httpsConnection.setHostnameVerifier((hostname, session) -> true);

        }

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

        } catch (Exception error) {

        	// Use example data when UC's API is down.
			String exampleData = "[{\"FacilityID\":\"2045\",\"Description\":\"CCM Garage\",\"Occupancy\":[{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"312\",\"Occupied\":\"108\",\"Available\":\"204\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"},{\"OccupancyType\":\"Transient\",\"Capacity\":\"125\",\"Occupied\":\"83\",\"Available\":\"42\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"},{\"OccupancyType\":\"Value Credential\",\"Capacity\":\"100\",\"Occupied\":\"0\",\"Available\":\"100\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"}]},{\"FacilityID\":\"2046\",\"Description\":\"Calhoun Garage\",\"Occupancy\":[{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"1000\",\"Occupied\":\"568\",\"Available\":\"432\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"},{\"OccupancyType\":\"Transient\",\"Capacity\":\"100\",\"Occupied\":\"42\",\"Available\":\"58\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"}]},{\"FacilityID\":\"2047\",\"Description\":\"Campus Green Garage\",\"Occupancy\":[{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"1500\",\"Occupied\":\"676\",\"Available\":\"824\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"},{\"OccupancyType\":\"Transient\",\"Capacity\":\"250\",\"Occupied\":\"150\",\"Available\":\"100\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"}]},{\"FacilityID\":\"2049\",\"Description\":\"Clifton Court Garage\",\"Occupancy\":[{\"OccupancyType\":\"Transient\",\"Capacity\":\"100\",\"Occupied\":\"28\",\"Available\":\"72\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"},{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"406\",\"Occupied\":\"141\",\"Available\":\"265\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"}]},{\"FacilityID\":\"2043\",\"Description\":\"Corry Garage\",\"Occupancy\":[{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"497\",\"Occupied\":\"260\",\"Available\":\"237\",\"Timestamp\":\"4/11/2023 8:55:19 AM\"},{\"OccupancyType\":\"Transient\",\"Capacity\":\"100\",\"Occupied\":\"58\",\"Available\":\"42\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]},{\"FacilityID\":\"2050\",\"Description\":\"Eden Garage\",\"Occupancy\":[{\"OccupancyType\":\"Transient\",\"Capacity\":\"300\",\"Occupied\":\"138\",\"Available\":\"162\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"2304\",\"Occupied\":\"1466\",\"Available\":\"838\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]},{\"FacilityID\":\"2052\",\"Description\":\"Kingsgate Garage\",\"Occupancy\":[{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"388\",\"Occupied\":\"122\",\"Available\":\"266\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Transient\",\"Capacity\":\"275\",\"Occupied\":\"88\",\"Available\":\"187\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Reserved\",\"Capacity\":\"0\",\"Occupied\":\"0\",\"Available\":\"0\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]},{\"FacilityID\":\"2051\",\"Description\":\"Stratford Garage\",\"Occupancy\":[{\"OccupancyType\":\"Transient\",\"Capacity\":\"2\",\"Occupied\":\"38\",\"Available\":\"-36\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"334\",\"Occupied\":\"351\",\"Available\":\"-17\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]},{\"FacilityID\":\"2061\",\"Description\":\"UPA Lot\",\"Occupancy\":[{\"OccupancyType\":\"Transient\",\"Capacity\":\"8000\",\"Occupied\":\"27\",\"Available\":\"7973\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"100\",\"Occupied\":\"5\",\"Available\":\"95\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]},{\"FacilityID\":\"2042\",\"Description\":\"University Ave Garage\",\"Occupancy\":[{\"OccupancyType\":\"Transient\",\"Capacity\":\"5\",\"Occupied\":\"64\",\"Available\":\"-59\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"1094\",\"Occupied\":\"539\",\"Available\":\"555\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Value Credential\",\"Capacity\":\"0\",\"Occupied\":\"0\",\"Available\":\"0\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]},{\"FacilityID\":\"2060\",\"Description\":\"University Ave Garage Level 1\",\"Occupancy\":[{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"300\",\"Occupied\":\"2088\",\"Available\":\"-1788\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Transient\",\"Capacity\":\"5\",\"Occupied\":\"124\",\"Available\":\"-119\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]},{\"FacilityID\":\"2044\",\"Description\":\"Varsity Village Garage\",\"Occupancy\":[{\"OccupancyType\":\"Transient\",\"Capacity\":\"45\",\"Occupied\":\"42\",\"Available\":\"3\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"100\",\"Occupied\":\"41\",\"Available\":\"59\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]},{\"FacilityID\":\"2048\",\"Description\":\"Woodside Garage\",\"Occupancy\":[{\"OccupancyType\":\"Transient\",\"Capacity\":\"150\",\"Occupied\":\"60\",\"Available\":\"90\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"},{\"OccupancyType\":\"Non-Transient\",\"Capacity\":\"712\",\"Occupied\":\"264\",\"Available\":\"448\",\"Timestamp\":\"4/11/2023 8:55:20 AM\"}]}]";
			return exampleData;

		} finally {

            urlConnection.disconnect();

        }

        return sb.toString();

    }

}
