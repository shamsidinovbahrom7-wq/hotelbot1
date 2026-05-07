package Bahrom;

import Bahrom.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot());

            System.out.println("Bot Run!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}