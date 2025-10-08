package my.example.Weather.bot.config;

import my.example.Weather.bot.WeatherBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Autowired
    private WeatherBot weatherBot;

    @Bean
    public TelegramBotsApi telegramBotsApi() throws Exception, TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(weatherBot); // Регистрация вашего бота
        return telegramBotsApi;
    }
}