package com.example.telegrambotchsedule.response;

import com.example.telegrambotchsedule.entity.BookEntity;

public class BookResponce extends BaseResponce{

    private BookEntity data;

    public BookResponce(boolean success, String message, BookEntity data){
        super(success,message);
        this.data = data;
    }

}
