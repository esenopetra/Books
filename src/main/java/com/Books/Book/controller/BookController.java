package com.Books.Book.controller;

import com.Books.Book.model.Book;
import com.Books.Book.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class BookController {
    @Autowired
    private BookRepo bookRepo;
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
    try{
        List<Book> bookList = new ArrayList<>();
        bookRepo.findAll().forEach(bookList::add);

        if(bookList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
    catch(Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        try{

            Optional<Book> bookData = bookRepo.findById(id);
            if (bookData.isPresent())
                return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/addBooks")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try{
            Book book1 = bookRepo.save(book);
            return new ResponseEntity<>(book1 , HttpStatus.CREATED);
        }catch(Exception e){return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}

    }
    @PostMapping("/updateBooks/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable long id, @RequestBody Book update_value){
        try{
            Optional<Book> bookData = bookRepo.findById(id);
            if(bookData.isPresent()){
                Book toUpdate = bookData.get();
               toUpdate.setBookName(update_value.getBookName());
               toUpdate.setBookSummary(update_value.getBookSummary());
               toUpdate.setAuthor(update_value.getAuthor());

                Book book1 = bookRepo.save(toUpdate);
                return new ResponseEntity<>(book1,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable long id){
        try{
            Optional<Book> bookData = bookRepo.findById(id);
            if(bookData.isPresent()){
               bookRepo.deleteById(id);
               return new ResponseEntity<>(HttpStatus.GONE);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }

    }
}
