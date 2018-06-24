package com.vineethkamisetty.api.controller;

import com.vineethkamisetty.api.constants.URI;
import com.vineethkamisetty.api.entity.CityWeather;
import com.vineethkamisetty.api.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // has both @Controller and @ResponseBody
@RequestMapping(value = "/api" + URI.CITIES)
@Api(tags = "User APIs")
public class UserAPIController {

    private WeatherService weatherService;

    public UserAPIController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find All Cities", notes = "Returns a list of Cities in the app")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error"),})
    public List<String> allCities() {
        return weatherService.allCities();
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.CITY)
    @ApiOperation(value = "Get City Weather", notes = "Returns Weather of a City in the app")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),})
    public CityWeather getWeather(@PathVariable("city") String city) {
        return weatherService.getWeather(city);
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.CITY_PROPERTY)
    @ApiOperation(value = "Get a City Weather Property", notes = "Returns the Weather property of a City in the app")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),})
    public String getWeatherProperty(@PathVariable("city") String city, @PathVariable("property") String property) {
        CityWeather cityWeather = weatherService.getWeatherProperty(city, property);    // not ideal to get complete entry for table with large number of columns. need to improve this.
        return cityWeather.getProperty(property);
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.HOURLY)
    @ApiOperation(value = "Get Hourly Average Weather Readings", notes = "Returns Hourly Average Weather Readings of a City in the app")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),})
    public CityWeather get_Hr_Avg_Weather(@PathVariable("city") String city) {
        return weatherService.get_Hr_Avg_Weather(city);
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.DAILY)
    @ApiOperation(value = "Get Daily Average Weather Readings", notes = "Returns Daily Average Weather Readings of a City in the app")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),})
    public CityWeather get_Day_Avg_Weather(@PathVariable("city") String city) {
        return weatherService.get_Day_Avg_Weather(city);
    }
}
