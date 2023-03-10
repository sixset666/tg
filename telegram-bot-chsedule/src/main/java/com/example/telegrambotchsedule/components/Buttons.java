package com.example.telegrambotchsedule.components;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Buttons {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton("Start");
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("Help");

    private static final InlineKeyboardButton ALL_BUTTON = new InlineKeyboardButton("All");


    public static InlineKeyboardMarkup inlineMarkup() {
        START_BUTTON.setCallbackData("/start");
        HELP_BUTTON.setCallbackData("/help");
        ALL_BUTTON.setCallbackData("/all");

        List<InlineKeyboardButton> rowInline = List.of(START_BUTTON, HELP_BUTTON,ALL_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);


        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);


        return markupInline;
    }
}
