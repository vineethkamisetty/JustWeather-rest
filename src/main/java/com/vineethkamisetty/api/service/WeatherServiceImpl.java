package com.vineethkamisetty.api.service;

import com.vineethkamisetty.api.database.Repository;
import com.vineethkamisetty.api.entity.CityWeather;
import com.vineethkamisetty.api.exception.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private Repository repository;

    public WeatherServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Transactional
    public void create(CityWeather cityWeather) { // returning void because it is reading from kinda read-only endpoint
        repository.addWeather(cityWeather);
    }

    @Transactional(readOnly = true)
    public List<String> allCities() {
        return repository.allCities();
    }

    @Transactional(readOnly = true)
    public CityWeather getWeather(String city) {
        CityWeather cityWeather = repository.getWeather(city);
        if (cityWeather == null)
            throw new NotFound(city + " is not present in database");
        return cityWeather;
    }

    @Transactional(readOnly = true)
    public CityWeather getWeatherProperty(String city, String property) {
        CityWeather cityWeather = repository.getWeatherProperty(city, property);
        if (cityWeather == null)
            throw new NotFound(city + " is not present in database");
        return cityWeather;
    }

    @Transactional(readOnly = true)
    public CityWeather get_Hr_Avg_Weather(String city) {
        CityWeather cityWeather = repository.get_Hr_Avg_Weather(city);
        if (cityWeather == null)
            throw new NotFound(city + " is not present in database");
        return cityWeather;
    }

    @Transactional(readOnly = true)
    public CityWeather get_Day_Avg_Weather(String city) {
        CityWeather cityWeather = repository.get_Day_Avg_Weather(city);
        if (cityWeather == null)
            throw new NotFound(city + " is not present in database");
        return cityWeather;
    }
}
