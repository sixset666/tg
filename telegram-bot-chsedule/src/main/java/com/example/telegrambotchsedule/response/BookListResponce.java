package com.example.telegrambotchsedule.response;


import com.example.telegrambotchsedule.entity.BookEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookListResponce extends BaseResponce {

    public BookListResponce(Iterable<BookEntity> data) {
        super(true, "Список книг");

        this.data = data;
    }
    private Iterable<BookEntity> data;

    @Override
    public String toString() {
        return "Книги из библиотеки\n" + data;
    }
}
