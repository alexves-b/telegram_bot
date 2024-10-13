package my.example.Weather.bot.servise;

import com.sun.research.ws.wadl.HTTPMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    private final String API_KEY = "618f3266441d9ec859db854ec9ed7c5f"; // Укажите свой API-ключ
    private final String BASE_URL = """
            https://api.openweathermap.org/data/2.5/weather""";


    public String getWeather(String cityName) {
        try {
            System.out.println("CityName=" + cityName);
            String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, cityName, API_KEY);
            // Выполняем GET-запрос
            System.out.println(url);
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

            if (response != null && response.getMain() != null) {
                return String.format("Погода в %s: %s°C, %s",
                        cityName,
                        response.getMain().getTemp(),
                        response.getWeather().getFirst().getDescription());
            } else {
                return "Не удалось получить данные о погоде.";
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println();
        }

        return null;
    }
}