/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.controller;

import com.example.golfclub.view.HTMLPage;
import com.example.golfclub.entity.Client;
import com.example.golfclub.repository.ClientRepository;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author max19
 */
@RestController
@RequestMapping("/signup")
public class SignupController {
    
    @Autowired
    ClientRepository clientRepository;
    
    @GetMapping()
    public String showPage() throws IOException {
        return new HTMLPage("src\\main\\resources\\static\\signup.html").toString();    
    }

    @PostMapping
    public String post(@Valid @RequestParam String name, String email, String login, String password, String birthDate) 
            throws ParseException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Client client = clientRepository.findByLogin(login).get();
            HTMLPage page = new HTMLPage("src\\main\\resources\\static\\signup.html");
            page.replaceFirst("Up</h2>", "Up</h2><h5>This login already exists</h5>");
            return page.toString();
        } catch(java.util.NoSuchElementException registrationAllowed){
            Client client = new Client();
            client.setName(name);
            client.setEmail(email);
            client.setLogin(login);
            client.setPassword(password);
            client.setBirthDate(format.parse(birthDate));
            clientRepository.save(client);
            HTMLPage page = new HTMLPage("src\\main\\resources\\static\\singletext.html");
            page.replaceFirst("change_this", "Registration complete");
            return page.toString();
        }
    }
    
}
