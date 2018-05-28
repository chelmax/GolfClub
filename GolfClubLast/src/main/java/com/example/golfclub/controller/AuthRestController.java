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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/login")
public class AuthRestController {
   
    @Autowired
    ClientRepository clientRepository;
    
    @GetMapping()
    public String showPage() throws IOException {
        return new HTMLPage("src\\main\\resources\\static\\login.html").toString();
    }

    @PostMapping()
    public String auth(HttpServletRequest request, @Valid @RequestParam String login, String password) throws IOException {
        try{
            Client client = clientRepository.findByLogin(login).get();
            if(client.getPassword().equals(password)){
                HttpSession session = request.getSession();
                session.setAttribute("userId", client.getIdClient());
                session.setAttribute("username", login);
                HTMLPage page = new HTMLPage("src\\main\\resources\\static\\singletext.html");
                page.replaceFirst("change_this", "Authorization complete");
                return  page.toString();
            }
            HTMLPage page = new HTMLPage("src\\main\\resources\\static\\login.html");
            page.replaceFirst("In</h2>", "In</h2><h5>Wrong password</h5>");
            return page.toString();
        } catch(java.util.NoSuchElementException ex){
            HTMLPage page = new HTMLPage("src\\main\\resources\\static\\login.html");
            page.replaceFirst("In</h2>", "In</h2><h5>Unregistered login</h5>");
            return page.toString();
        }
    }
    
}
