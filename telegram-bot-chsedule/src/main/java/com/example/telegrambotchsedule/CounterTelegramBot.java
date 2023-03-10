package com.example.telegrambotchsedule;

import com.example.telegrambotchsedule.components.BotCommands;
import com.example.telegrambotchsedule.components.Buttons;
import com.example.telegrambotchsedule.config.BotConfig;
import com.example.telegrambotchsedule.entity.BookEntity;
import com.example.telegrambotchsedule.response.BookListResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.validation.constraints.NotNull;

    @Slf4j
    @Component
    public class CounterTelegramBot extends TelegramLongPollingBot implements BotCommands {
        final BotConfig config;


        public CounterTelegramBot(BotConfig config) {
            this.config = config;
            try {
                this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
            } catch (TelegramApiException e){
                log.error(e.getMessage());
            }
        }


        @Override
        public String getBotUsername() {
            return config.getBotName();
        }


        @Override
        public String getBotToken() {
            return config.getToken();
        }


        @Override
        public void onUpdateReceived(@NotNull Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();
                String memberName = update.getMessage().getFrom().getFirstName();

                switch (messageText) {
                    case "/start":
                        startBot(chatId, memberName);
                        break;
                    case "/all":
                        getAllBook(chatId);
                        break;
                    default:
                        log.info("Unexpected message");
                }
            }
        }
        private void getAllBook(long chatId) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            ResponseEntity<BookListResponce> responseEntity = new RestTemplate().getForEntity("http://localhost:2825/api/v1/book/all", BookListResponce.class);
            System.out.println(responseEntity.getBody().getData());
            message.setText(responseEntity.getBody().getData().toString().replaceAll("^,\\[|\\]$",""));

            try {
                execute(message);
                log.info("Reply sent");
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }

        private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
            switch (receivedMessage){
                case "/start":
                    startBot(chatId, userName);
                    break;
                case "/help":
                    sendHelpText(chatId, HELP_TEXT);
                    break;
                case "/all":
                    getAllBook(chatId,);
                default: break;
            }
        }


        private void startBot(long chatId, String userName) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Hi, " + userName + "! I'm a Telegram bot.'");
            message.setReplyMarkup(Buttons.inlineMarkup());


            try {
                execute(message);
                log.info("Reply sent");
            } catch (TelegramApiException e){
                log.error(e.getMessage());
            }
        }


        private void sendHelpText(long chatId, String textToSend){
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(textToSend);


            try {
                execute(message);
                log.info("Reply sent");
            } catch (TelegramApiException e){
                log.error(e.getMessage());
            }
        }
    }



