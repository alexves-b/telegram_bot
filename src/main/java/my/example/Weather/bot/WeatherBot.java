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


    @Override
    public String getBotUsername() {
        return " Italy_Supprot_bot";
    }

    @Override
    public String getBotToken() {
        return "";
    }


    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
                sendMessage(chatId, "Постоянный чат-бот Техподдержки Итали переехал сюда\n----> @italy_support_bot");

        }
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

