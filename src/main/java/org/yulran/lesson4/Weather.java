package org.yulran.lesson4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Weather {
    String cityName;
    String apiKey = "36245c78af5309a3dd3cc1d532eedfe1";
    String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=" + apiKey;

    private static Map<String, Object> parseJsonToMap(String json) {
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return new Gson().fromJson(json, type);
    }


    String getWeatherInfo(String cityName) {
        this.cityName = cityName;
        String Url = String.format(url, cityName, apiKey);
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String weatherData = response.body();      //.get("temp")
                Map<String, Object> jsonMap = parseJsonToMap(weatherData);
                List<Map<String, Object>> weatherList = (List<Map<String, Object>>) jsonMap.get("weather");
                String description = weatherList.get(0).get("description").toString();
                double tempKelvin = Double.parseDouble(jsonMap.get("main.temp").toString());
                double tempCelsius = Math.round(tempKelvin - 273.15);
                return "Температура: " + tempCelsius + " градусов Цельсия " + description;
            } else {
                return "Ошибка: " + response.statusCode();
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return "Ошибка: " + e.getMessage();
        }
    }
}
