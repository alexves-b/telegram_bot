package my.example.Weather.bot.servise;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;


    public String getWeather(String cityName) {
        try {
            System.out.println("CityName=" + cityName);
            String BASE_URL = """
                    https://api.openweathermap.org/data/2.5/weather""";
            // Укажите свой API-ключ
            String API_KEY = "618f3266441d9ec859db854ec9ed7c5f";
            String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, cityName, API_KEY);
                       HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json"); // Добавьте другие заголовки, если необходимо
            headers.set("User-Agent", "PostmanRuntime/7.42.0");
            headers.set("Connection", "keep-alive");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            System.out.println(url);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherResponse.class);

            if (response != null ) {
                return String.format("Погода в городе %s: %s°C, %s "
                            +System.lineSeparator()+ " Ощущаемая температура: %s"
                            + System.lineSeparator()+ " Влажность воздуха составляет: %s процентов"
                            + System.lineSeparator()+ " Скорость ветра: %s метров в секунду ",
                    cityName,
                    response.getBody().getMain().getTemp(),
                    response.getBody().getWeather().get(0).getDescription(),
                    response.getBody().getMain().getFeelsLike(),
                    response.getBody().getMain().getHumidity(),
                    response.getBody().getWind().getSpeed());

            } else {
                return "Не удалось получить данные о погоде.";
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println( e.fillInStackTrace());
        }
        return null;
    }
}