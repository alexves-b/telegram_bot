package my.example.Weather.bot;

import lombok.extern.slf4j.Slf4j;
import my.example.Weather.bot.servise.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

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
                System.out.println(chatId);
                String weatherInfo = getWeather(cityName);
                sendMessage(chatId, weatherInfo);
                saveWeather(cityName,weatherInfo,chatId);
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
        return response;
    }

    private void saveWeather(String request,String weatherInfo,String chatId) {
        weatherService.saveInfo(chatId,request,weatherInfo);
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

