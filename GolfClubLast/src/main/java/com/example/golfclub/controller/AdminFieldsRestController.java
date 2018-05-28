/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.controller;

import com.example.golfclub.entity.Field;
import com.example.golfclub.repository.FieldRepository;
import com.example.golfclub.view.FieldsInfoPage;
import java.io.IOException;
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
@RequestMapping("/admin_fields")
public class AdminFieldsRestController {
    
    @Autowired
    FieldRepository fieldRepository;
    private static final String FILEPATH = 
            "src\\main\\resources\\static\\admin_fields.html"; 
    
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
    public String post(@Valid @RequestParam String fieldName, String size, String pricePerDay, String choose) 
            throws IOException {
        if(choose == null)
            return createPage().toString();
        if(choose.equals("Изменить")){
            Field field = fieldRepository.findByFieldName(fieldName).get();
            field.setFieldName(fieldName);
            try {
                field.setSize(Float.parseFloat(size));
                field.setPricePerDay(Integer.parseInt(pricePerDay));
            } catch (NumberFormatException wrongData) {
                return wrongEditInput().toString();
            }
            fieldRepository.save(field);
        }else {
            Field field = fieldRepository.findByFieldName(fieldName).get();
            fieldRepository.delete(field);
        }
        return createPage().toString();
    }
    
    private FieldsInfoPage wrongEditInput() throws IOException{
        FieldsInfoPage page  = createPage();
        page.wrongEditInput();
        return page;
    }
    
    private FieldsInfoPage createPage() throws IOException{
        List<Field> fields = fieldRepository.findAll();
        FieldsInfoPage page  = new FieldsInfoPage(FILEPATH);
        fields.forEach((field) -> {
            page.addField(field);
        });
        return page;
    } 
}
