/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.controller;

import com.example.golfclub.view.UsersInfoPage;
import com.example.golfclub.entity.Client;
import com.example.golfclub.repository.ClientRepository;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author max19
 */
@RestController
@RequestMapping("/admin_users")
public class AdminUsersRestController {
    
    @Autowired
    ClientRepository clientRepository;
    private static final String FILEPATH = 
            "src\\main\\resources\\static\\admin_users.html"; 
    
    @GetMapping()
    public String showPage(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username.equals("Admin")){
            return createPage().toString();
        } else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
    
    @PostMapping()
    public String post(@Valid @RequestParam String login, String name, String email, String date, String choose) 
            throws IOException {
        if(choose == null)
            return createPage().toString();
        if(choose.equals("Изменить")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
            Client client = clientRepository.findByLogin(login).get();
            client.setName(name);
            client.setEmail(email);
            try {
                client.setBirthDate(format.parse(date));
            } catch (ParseException wrongData) {
                return wrongEditInput().toString();
            }
            clientRepository.save(client);
        }else {
            Client client = clientRepository.findByLogin(login).get();
            clientRepository.delete(client);
        }
        return createPage().toString();
    }
    
    private UsersInfoPage wrongEditInput() throws IOException{
        List<Client> clients = clientRepository.findAll();
        UsersInfoPage page  = createPage();
        page.wrongEditInput();
        return page;
    }
    
    private UsersInfoPage createPage() throws IOException{
        List<Client> clients = clientRepository.findAll();
        UsersInfoPage page  = new UsersInfoPage(FILEPATH);
        clients.forEach((client) -> {
            page.addUser(
                    client.getName(),
                    client.getEmail(),
                    client.getLogin(), 
                    client.getBirthDate()
            );
        });
        return page;
    }
    
}
