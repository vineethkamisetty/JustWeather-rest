package com.vineethkamisetty.api.controller;

import com.vineethkamisetty.api.constants.URI;
import com.vineethkamisetty.api.entity.CityWeather;
import com.vineethkamisetty.api.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController     // has both @Controller and @ResponseBody
@Api(tags = "DB APIs")
public class WeatherDBInfoController {

    private WeatherService weatherService;

    public WeatherDBInfoController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(method = RequestMethod.POST, value = URI.WEATHER)
    @ApiOperation(value = "Get Weather Data", notes = "Stores the Weather Data form a mocker into database")
    //consume and produce attribute not required in new version of spring and need to check how to change localhost:8080
    public void create(@RequestBody CityWeather cityWeather) {
        System.out.println(cityWeather.toString());
        weatherService.create(cityWeather);
    }
}
