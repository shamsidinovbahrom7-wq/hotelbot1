package Bahrom;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    private final Handler handler;

    public Bot() {
        this.handler = new Handler(this);
    }

    @Override
    public String getBotUsername() { return Config.USERNAME; }

    @Override
    public String getBotToken() { return Config.TOKEN; }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            handler.handle(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}