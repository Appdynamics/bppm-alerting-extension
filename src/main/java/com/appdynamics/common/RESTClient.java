/**
 * Copyright 2013 AppDynamics
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appdynamics.common;
/**
 *  This class is a generic REST client.
 *
 * Copyright (c) AppDynamics, Inc.
 * @author Pranta Das
 * Created on: September 18, 2012.
 */

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTClient {

    private static Logger logger =
                Logger.getLogger(RESTClient.class);


	public static void sendGet(String urlString, String apiKey) {

	  try {

		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Basic " + new String(Base64.encodeBase64((apiKey).getBytes())));

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		logger.info("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			logger.info(output);
		}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

	}

	public static void sendPost(String urlString, String input, String apiKey) {
	  try
      {
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

        conn.setRequestProperty("Authorization", "Basic " + new String(Base64.encodeBase64((apiKey).getBytes())));

		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		logger.info("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			logger.info(output);
		}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();


	  } catch (IOException e) {

		e.printStackTrace();

	 }

	}


}

