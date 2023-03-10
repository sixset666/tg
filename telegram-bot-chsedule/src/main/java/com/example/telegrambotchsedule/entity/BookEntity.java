package com.example.telegrambotchsedule.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookEntity {
    @Column(name = "book_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private AuthorEntity author;
    private PublisherEntity publishing;
    private int year;
    private String kind;

    public String toString() {
        return  "Название: " + title + '\n' +
                "Автор: " + author + '\n' +
                "Издательство: " + publishing + '\n' +
                "Год издания: " + year + '\n' +
                "Жанр: " + kind + '\n' +
                '\n';
    }
}
