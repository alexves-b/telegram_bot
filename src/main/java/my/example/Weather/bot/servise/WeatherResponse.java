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

    @JsonProperty("weather")
    private List<Weather> weather; // Список погоды

    // Геттеры и Сеттеры...

    public List<Weather> getWeather() {
        return weather;
    }

    public MainWeather getMain() {
        return main;
    }
}
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class MainWeather {
    private float temp;
}
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Weather {
    private String description;
}