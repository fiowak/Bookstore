package com.example.Bookstore.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.Domain.Book;
import com.example.Bookstore.Domain.BookRepository;

import com.example.Bookstore.Domain.UserRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private BookRepository repository;
    @Autowired
    private UserRepository urepository;

    @RequestMapping("/index")
    public String home() {
        return "index";
    }


    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/booklist")
    public String listbooks(Model model) {
        model.addAttribute("Books", repository.findAll());
        return "booklist";

    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        repository.delete(id);
        return "redirect:../booklist";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody
    List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }



}
