package org.yulran.lesson4;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class BotModule {
    public BotModule(){
        ArrayList<BotCommand> commandsList = new ArrayList<>();
        commandsList.add(new BotCommand("/weather", "Показывает погоду в указанном городе"));

        try{
            this.execute(new SetMyCommands(commandsList, new BotCommandScopeDefault(), null));
        }catch (TelegramApiException e){
            System.out.println("Error with executing commands menu: "+e);
        }
    }



}
