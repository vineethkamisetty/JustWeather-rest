package com.vineethkamisetty.api.database;

import com.vineethkamisetty.api.entity.CityWeather;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class RepositoryImpl implements Repository {

    @PersistenceContext
    private EntityManager em;

    public void addWeather(CityWeather cityWeather) {
        em.persist(cityWeather);
    }

    public List<String> allCities() {
        TypedQuery<String> query = em.createQuery("SELECT DISTINCT c.city FROM CityWeather c", String.class);
        System.out.println(query.getResultList());
        return query.getResultList();
    }

    public CityWeather getWeather(String city) {
        return getCityWeather(city);
    }

    public CityWeather getWeatherProperty(String city, String property) {
        return getCityWeather(city);
    }

    private CityWeather getCityWeather(String city) {
        System.out.println("RepositoryImpl :: City: " + city);
        TypedQuery<CityWeather> query = em.createQuery("SELECT c FROM CityWeather c WHERE c.city=:pcity ORDER BY c.uid DESC", CityWeather.class);
        query.setParameter("pcity", city);
        List<CityWeather> cityWeathers = query.getResultList();
        if (cityWeathers.isEmpty())
            return null;
        System.out.println("RepositoryImpl :: cityWeather: " + cityWeathers.get(0).toString());
        return cityWeathers.get(0);
    }


    public CityWeather get_Hr_Avg_Weather(String city) {
        return null;
    }

    public CityWeather get_Day_Avg_Weather(String city) {
        return null;
    }
}
