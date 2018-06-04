/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.controller;

import com.example.golfclub.entity.Field;
import com.example.golfclub.repository.FieldRepository;
import com.example.golfclub.view.HTMLPage;
import com.example.golfclub.view.MainPage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/")
public class MainRestController {
    
    @Autowired
    FieldRepository fieldRepository;
    private static final String FILEPATH = 
            "src\\main\\resources\\static\\index.html"; 
    private static final String MESSAGE_FILEPATH = 
            "src\\main\\resources\\static\\singletext.html";
    
    
    @GetMapping()
    public String showPage(HttpServletRequest request) throws IOException {
        MainPage page = new MainPage(FILEPATH);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username != null){
            if(username.equals("Admin"))
                page.navBarForAdmin();
            else{
                int ordersCount = 0;
                String busket = (String) session.getAttribute("busket");
                if(busket != null)
                    ordersCount = busket.split("#").length;                     
                page.navBarForAuthorized(username, ordersCount); 
            }
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
    
    @PostMapping
    public String addToBusket(HttpServletRequest request, @Valid @RequestParam String nameButton, String leaseStart, String leaseEnd) 
            throws IOException, ParseException{
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username == null){
            HTMLPage page = new HTMLPage(MESSAGE_FILEPATH);
            page.replaceFirst("change_this", "You have to be authorized to make orders");
            return  page.toString();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse(leaseStart);
        Date end = format.parse(leaseEnd);
        if(start.after(end)){
            HTMLPage page = new HTMLPage(MESSAGE_FILEPATH);
            page.replaceFirst("change_this", "Start date can't be after end's date");
            return  page.toString();
        }
        String orderInfo = nameButton;
        String attr = (String) session.getAttribute("busket"); 
        StringBuilder sb = new StringBuilder();
        if(attr != null){
            sb.append(attr).append("#");
        } 
        sb.append(orderInfo).append("$")
                .append(leaseStart)
                .append("$")
                .append(leaseEnd);
        session.setAttribute("busket", sb.toString());
        return showPage(request);
     }
    
}
