/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.controller;

import com.example.golfclub.entity.Client;
import com.example.golfclub.entity.Field;
import com.example.golfclub.entity.Orders;
import com.example.golfclub.repository.ClientRepository;
import com.example.golfclub.repository.FieldRepository;
import com.example.golfclub.repository.OrdersRepository;
import com.example.golfclub.view.HTMLPage;
import com.example.golfclub.view.UsersBusketPage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author max19
 */
@RestController
@RequestMapping("/busket")
public class UsersBusketRestController {
    
    @Autowired
    FieldRepository fieldRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrdersRepository ordersRepository;
    private static final String FILEPATH = 
            "src\\main\\resources\\static\\busket.html"; 
    private static final String MESSAGE_FILEPATH = 
            "src\\main\\resources\\static\\singletext.html";
    
    
    @GetMapping()
    public String showPage(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username != null){
            UsersBusketPage page  = new UsersBusketPage(FILEPATH);
            String busket = (String) session.getAttribute("busket");
            if(busket == null)
                page.emptyBusket();
            else{
                page.orderButton();
                List<String> orders = Arrays.asList(busket.split("#"));
                orders.forEach((order) -> {
                    String[] orderInfo = order.split("[$]");
                    page.addOrderInfo(orderInfo[0], orderInfo[1], orderInfo[2]);
                });
            }
            return page.toString();
        } else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
    
    @PostMapping
    public String addToBusket(HttpServletRequest request) 
            throws IOException, ParseException{
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Client client  = clientRepository.findByLogin(username).get();
        String busket = (String) session.getAttribute("busket");
        List<String> orders = Arrays.asList(busket.split("#"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        orders.forEach((order) -> {
            String[] orderInfo = order.split("[$]");
            Field field = fieldRepository.findByFieldName(orderInfo[0]).get();
            Orders o = new Orders();
            o.setIdClient(client);
            o.setIdField(field);
            try {
                o.setLeaseStart(format.parse(orderInfo[1]));
                o.setLeaseEnd(format.parse(orderInfo[2]));
            } catch (ParseException ex) {
                Logger.getLogger(UsersBusketRestController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            ordersRepository.save(o);
        });
        session.setAttribute("busket", null);
        HTMLPage page = new HTMLPage(MESSAGE_FILEPATH);
        page.replaceFirst("change_this", "Order was successfully made");
        return  page.toString();
     }
    
}
