package com.example.demo.servise;

import com.example.demo.entity.BookEntity;
import com.example.demo.repo.BookRepo;
import org.springframework.stereotype.Service;

@Service
public class   BookService {
    private final BookRepo repo;
    public BookService(BookRepo repo){
        this.repo = repo;
    }
    public BookEntity save(BookEntity book){
       return repo.save(book);
    }
    public void delete(long id){
        repo.deleteById(id);
    }
    public Iterable<BookEntity> getAll(){
        return repo.findAll();
    }
}
