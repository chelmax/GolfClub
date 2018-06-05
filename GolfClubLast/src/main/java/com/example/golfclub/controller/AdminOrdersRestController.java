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
import com.example.golfclub.view.OrdersInfoPage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/admin_orders")
public class AdminOrdersRestController {
    
    @Autowired
    OrdersRepository ordersRepository;
    private static final String FILEPATH = 
            "src\\main\\resources\\static\\admin_orders.html"; 
    
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
    public String post(@Valid @RequestParam String id, String login, String fieldName, String leaseStart, String leaseEnd, String choose) 
            throws IOException {
        if(choose == null)
            return createPage().toString();
        Orders order = ordersRepository.findById(Integer.parseInt(id)).get();
        if(choose.equals("Change")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
            try {
                order.setLeaseStart(format.parse(leaseStart));
                order.setLeaseEnd(format.parse(leaseEnd));
            } catch (ParseException wrongData) {
                return wrongEditInput().toString();
            }
            ordersRepository.save(order);
        }else {
            ordersRepository.delete(order);
        }
        return createPage().toString();
    }
    
    private OrdersInfoPage wrongEditInput() throws IOException{
        OrdersInfoPage page  = createPage();
        page.wrongEditInput();
        return page;
    }
    
    private OrdersInfoPage createPage() throws IOException{
        List<Orders> orders = ordersRepository.findAll();
        OrdersInfoPage page  = new OrdersInfoPage(FILEPATH);
        orders.forEach((order) -> {
            page.addOrder(order);
        });
        return page;
    }
}
