/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.controller;

import com.example.golfclub.entity.Client;
import com.example.golfclub.entity.Field;
import com.example.golfclub.repository.ClientRepository;
import com.example.golfclub.repository.FieldRepository;
import com.example.golfclub.view.MainPage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author max19
 */
@RestController
@RequestMapping("/")
public class MainController {
    
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    FieldRepository fieldRepository;
    
    
    @GetMapping()
    public String showPage(HttpServletRequest request) 
            throws FileNotFoundException, IOException {
        MainPage page = new MainPage("src\\main\\resources\\static\\index.html");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username != null){
            Client client = clientRepository.findByLogin(username).get();
            Integer ordersCount = client.getOrdersCollection().size();
            if(username.equals("Admin"))
                page.navBarForAdmin();
            else
               page.navBarForAuthorized(username, ordersCount); 
        }
        List<Field> fields = fieldRepository.findAll();
        for(int i = 0; i < fields.size(); i++){
            Field field = fields.get(i);
            page.addField(
                    field.getFieldName(), 
                    field.getIdCategory().getType(), 
                    field.getIdOwner().getName(), 
                    field.getSize(), 
                    field.getPricePerDay(),
                    i
            );
        }
        return page.toString();
    }
    
}
