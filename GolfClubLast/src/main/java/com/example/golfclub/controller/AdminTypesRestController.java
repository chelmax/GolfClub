/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.controller;

import com.example.golfclub.entity.Category;
import com.example.golfclub.repository.CategoryRepository;
import com.example.golfclub.view.CategoriesInfoPage;
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
@RequestMapping("/admin_categories")
public class AdminTypesRestController {
    
    @Autowired
    CategoryRepository categoryRepository;
    private static final String FILEPATH = 
            "src\\main\\resources\\static\\admin_categories.html"; 
    
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
    public String post(@Valid @RequestParam String type, String choose) 
            throws IOException {
        if(choose == null)
            return createPage().toString();
        if(choose.equals("Изменить")){
            Category category = categoryRepository.findByType(type).get();
            category.setType(type);
            categoryRepository.save(category);
        } else if(choose.equals("Add new")){
            Category category = new Category();
            category.setType(type);
            categoryRepository.save(category);
        }else {
            Category category = categoryRepository.findByType(type).get();
            categoryRepository.delete(category);
        }
        return createPage().toString();
    }
    
    private CategoriesInfoPage createPage() throws IOException{
        List<Category> categorys = categoryRepository.findAll();
        CategoriesInfoPage page  = new CategoriesInfoPage(FILEPATH);
        categorys.forEach((category) -> {
            page.addCategory(category.getType());
        });
        return page;
    } 
    
}
