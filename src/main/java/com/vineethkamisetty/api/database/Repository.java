package com.vineethkamisetty.api.database;

import com.vineethkamisetty.api.entity.CityWeather;

import java.util.List;

public interface Repository {

    void addWeather(CityWeather cityWeather);

    List<String> allCities();

    CityWeather getWeather(String city);

    CityWeather getWeatherProperty(String city, String property);

    CityWeather get_Hr_Avg_Weather(String city);

    CityWeather get_Day_Avg_Weather(String city);
}
