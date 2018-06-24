package com.vineethkamisetty.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Map;

@Entity
@NamedQueries({
        @NamedQuery(name = "CityWeather.allCities", query = "SELECT DISTINCT c.city FROM CityWeather c"),
        @NamedQuery(name = "CityWeather.getLatest", query = "SELECT c FROM CityWeather c WHERE c.city=:pcity ORDER BY uid DESC"),
        //@NamedQuery(name = "CityWeather.get_Hr_Avg_Weather", query = "SELECT c FROM CityWeather c WHERE city =: pcity ORDER BY uid DESC"),
        //@NamedQuery(name = "CityWeather.get_Day_Avg_Weather", query = "SELECT * FROM CityWeather c WHERE city =: pcity ORDER BY uid DESC ")
})

public class CityWeather {

    @Id
    @GeneratedValue
    private long uid;
    @JsonProperty("city")       //tells jackson to map 'city' attribute in json to this property
    private String city;
    @JsonProperty("description")    // need to check how to do with jsonfilter
    private String description;
    @JsonProperty("humidity")
    private int humidity;
    @JsonProperty("pressure")
    private int pressure;
    @JsonProperty("temperature")
    private int temperature;
    @JsonProperty("timestamp")
    private String timestamp;
    private double speed;
    private int degree;

    @JsonProperty("wind")
    private void unpack(Map<String, Object> wind) {
        System.out.println(wind.get("speed") + " " + wind.get("degree"));
        this.speed = (Double) wind.get("speed");
        this.degree = (Integer) wind.get("degree");

    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "uid='" + uid + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", temperature=" + temperature +
                ", timestamp='" + timestamp + '\'' +
                ", speed=" + speed +
                ", degree=" + degree +
                '}';
    }


    public String getProperty(String property) {
        switch (property) {
            case "temperature":
                return "Temperature at " + city + " is " + temperature;
            case "humidity":
                return "Humidity at " + city + " is " + humidity;
            case "wind":
                return "Wind in " + city + " speed is " + speed + " and direction is " + degree;
            case "pressure":
                return "Pressure at " + city + " is " + pressure;
        }
        return "The selected property is not available. Please select among Temperature, Humidity, Wind or Pressure";
    }
}
