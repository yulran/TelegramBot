package org.yulran.lesson4;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends  TelegramLongPollingBot{
    private String username ="WeatherBot ";
   private final String token = "6309360673:AAF4BonR8cx6g9oGeViLMAESxA064cFSAWA";
   private Weather weather = new Weather();
   private DataBase db = new DataBase();
    public TelegramBot(){ArrayList<BotCommand> commandsList = new ArrayList<>();
        commandsList.add(new BotCommand("/weather", "Показывает погоду в указанном городе"));

        try{
        this.execute(new SetMyCommands(commandsList, new BotCommandScopeDefault(), null));
    }catch (TelegramApiException e){
        System.out.println("Error with executing commands menu: "+e);
    }
}

        public static void main(String[] args) throws TelegramApiException {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            try {
                botsApi.registerBot(new TelegramBot());
                System.out.println("Bot started!");
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpdateReceived(Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                if (messageText.startsWith("/weather")) {
                    String cityName = messageText.substring(9).trim();
                    String weatherInfo = weather.getWeatherInfo(cityName);
                    sendResponse(update.getMessage().getChatId().toString(), weatherInfo);
                    db.saveWeatherToDatabase(cityName, weatherInfo);
                }
            }
        }


        @Override
        public String getBotUsername() {

            return username;
        }

        @Override
        public String getBotToken() {

            return token;
        }

        @Override
        public void onRegister() {
            super.onRegister();
        }


        @Override
        public void onUpdatesReceived(List<Update> updates) {
            super.onUpdatesReceived(updates);


        }
    private void sendResponse(String chatId, String text) {
        SendMessage message = new SendMessage();
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    }




