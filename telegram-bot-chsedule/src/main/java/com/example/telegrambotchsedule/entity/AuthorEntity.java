package com.example.telegrambotchsedule.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "authors")

public class AuthorEntity {

    @Id
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    @JsonIgnore
    private List<BookEntity> book;

    @Override
    public String toString() {
        return name + " " + lastname;
    }

}
