package com.top10Integration.serpAPI.Controllers;

import com.top10Integration.serpAPI.Models.Entity.Author;
import com.top10Integration.serpAPI.Models.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping({"/authors","/"})
    public String listAll(Model model){
        model.addAttribute("authors", service.listAll());
       return "authors";
    }

    @GetMapping("/authors/search")
    public String searchAuthor(Model model){
        Author author = new Author();
        model.addAttribute("author", author);
        return "search_author";
    }

    @PostMapping("/authors")
    public String saveAuthor(@ModelAttribute("author") String name, String author_ID){
        service.saveAuthor(name, author_ID);
        return "redirect:/authors";
    }

    @GetMapping("/authors/edit/{id}")
    public String editAuthor(@PathVariable Integer id, Model model ){
        model.addAttribute("author", service.findAuthorByID(id));
        return "edit_author";
    }

    @PostMapping("/authors/{id}")
    public String updateAuthor(@PathVariable Integer id, @ModelAttribute("authors") Author author, Model model){
        Author authorNew = service.findAuthorByID(id);
        authorNew.setId(id);
        authorNew.setAuthor_ID(author.getAuthor_ID());
        authorNew.setName(author.getName());
        authorNew.setEmail(author.getEmail());
        authorNew.setAuthor_url(author.getAuthor_url());
        authorNew.setAffiliations(author.getAffiliations());

        service.updateAuthor(authorNew);
        return "redirect:/authors";
    }

    @GetMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable Integer id){
        service.deleteAuthor(id);
        return "redirect:/authors";
    }
}
