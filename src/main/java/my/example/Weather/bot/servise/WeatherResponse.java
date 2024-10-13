package my.example.Weather.bot.servise;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем неизвестные поля
public class WeatherResponse {


    @JsonProperty("main")
    private MainWeather main; // Основные параметры погоды

    @JsonProperty("wind")
    private Wind wind; // Основные параметры погоды



    @JsonProperty("weather")
    private List<Weather> weather; // Список погоды


    public List<Weather> getWeather() {
        return weather;
    }

    public MainWeather getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class MainWeather {

    private float temp;
    @JsonProperty("feels_like")
    private double feelsLike;

    public float getTemp() {
        return temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public int getHumidity() {
        return humidity;
    }

    private int humidity;
}
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Weather {
    private String description;
}

@Getter
class Wind {
    private double speed;
}