package my.example.Weather.bot;

import jakarta.ws.rs.core.Request;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.example.Weather.bot.servise.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


@Slf4j
@Component
public class WeatherBot extends TelegramLongPollingBot {

    @Autowired
    private WeatherService weatherService;

    @Override
    public String getBotUsername() {
        return "weatherTgBobrAiBot";
    }

    @Override
    public String getBotToken() {
        return "7793921994:AAFM6yZRCzdtZK_gHB6yudfG1qslp_ydKXU";
    }


    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            if (userMessage.startsWith("/weather")) {
                String cityName = userMessage.substring(9).trim();
                String weatherInfo = getWeather(cityName);
                sendMessage(chatId, weatherInfo);
                log.debug(cityName);
            } else {
                sendMessage(chatId, " Вы ввели неверную команду. Для получения погоды, введите команду /weather <название города>");
            }
        }
    }

    private String getWeather(String cityName) {

        String response =  weatherService.getWeather(cityName);

        if(response == null) {
            return "Погоду не удалось получить";
        }
        // Вызов вашего сервиса получения данных о погоде
        // Имплементируйте логику получения данных о погоде из API
        return response; // Замените реальной логикой
    }

    private void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

