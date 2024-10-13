package my.example.Weather.bot.servise;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем неизвестные поля
public class WeatherResponse {

    private String name; // Название города

    @JsonProperty("main")
    private MainWeather main; // Основные параметры погоды

    @JsonProperty("weather")
    private List<Weather> weather; // Список погоды

    // Геттеры и Сеттеры...

    public String getName() {
        return name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public MainWeather getMain() {
        return main;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class MainWeather {
    private float temp;

    // Геттеры и Сеттеры...

    public float getTemp() {
        return temp;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Weather {
    private String description;

    // Геттеры и Сеттеры...

    public String getDescription() {
        return description;
    }
}