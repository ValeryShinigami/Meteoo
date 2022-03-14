package com.mycompagny.meteoo.meteoo;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApplicationMain {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		String forecastUrl = "https://api.openweathermap.org/data/2.5/onecall?lat=48.856614&lon=2.3522219&units=metric&lang=fr&appid=63ce7b5714b3a0323be762f785dbfb52";
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(forecastUrl)
				.build();

		Response response;
		
		try {
			response = client.newCall(request).execute();

			//Réponse de ma requête
			String jsonData = response.body().string();
					
			//Representation de l'objet json
			JSONObject obj = (JSONObject) JSONValue.parse(jsonData);
			
			//Récupération zone géographique
			String timezone = (String) obj.get("timezone");
			
			//Dans current pour récuperer la température
			JSONObject current = (JSONObject) obj.get("current");
			double temp = Double.parseDouble(current.get("temp")+""); 
			
			//Affichage console 
			System.out.println("Donnée JSON: \n"+jsonData);
			System.out.println("Zone géographique: \n"+timezone);
			System.out.println("Température: \n"+temp+" degrés");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
