/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.controller;

import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import com.example.golfclub.entity.Category;
import com.example.golfclub.entity.Field;
import com.example.golfclub.entity.Owner;
import com.example.golfclub.repository.CategoryRepository;
import com.example.golfclub.repository.FieldRepository;
import com.example.golfclub.repository.OwnerRepository;
import com.example.golfclub.view.AddFieldPage;
import com.example.golfclub.view.HTMLPage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author max19
 */
@RestController
@RequestMapping("/add_field")
public class AddFieldRestController {
    
    @Autowired
    FieldRepository fieldRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    CategoryRepository categoryRepository;
    private static final String FILEPATH = 
            "src\\main\\resources\\static\\add_field.html"; 
    
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
    public String post(@Valid @RequestParam String fieldName, int owner, int category, float size, int pricePerDay, StandardMultipartHttpServletRequest multReq) 
            throws IOException {
        try{
            Field field = fieldRepository.findByFieldName(fieldName).get();
            List<Owner> owners = ownerRepository.findAll();
            List<Category> categories = categoryRepository.findAll();
            HTMLPage page  = new AddFieldPage(FILEPATH, owners, categories);
            page.replaceFirst("Adding Form</h2>", "Adding Form</h2><h5>This field already exists</h5>");
            return page.toString();
        } catch(java.util.NoSuchElementException registrationAllowed){
            Field field = new Field();
            field.setFieldName(fieldName);
            field.setIdOwner(ownerRepository.findById(owner).get());
            field.setIdCategory(categoryRepository.findById(category).get());
            field.setSize(size);
            field.setPricePerDay(pricePerDay);
            fieldRepository.save(field); 
            MultipartFile image = multReq.getFile("image");
            File full = new File("src\\main\\resources\\static\\img\\fields\\" + fieldName + "-full.jpg");
            full.createNewFile();
            File thumbnail = new File("src\\main\\resources\\static\\img\\fields\\" + fieldName + "-thumbnail.jpg");
            thumbnail.createNewFile();
            convertAndSave(image, full);
            convertAndSave(image, thumbnail);
            HTMLPage page = new HTMLPage("src\\main\\resources\\static\\singletext.html");
            page.replaceFirst("change_this", "Field successfully added");
            return page.toString();
        }
    }
    
    private void convertAndSave(MultipartFile image, File file){
        try{
            FileOutputStream fos = new FileOutputStream(file); 
            fos.write(image.getBytes());
            fos.close();
        } catch (IOException ex){
            System.err.println(ex);
        }
    }
    
    private HTMLPage createPage() throws IOException{
        List<Owner> owners = ownerRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        HTMLPage page  = new AddFieldPage(FILEPATH, owners, categories);
        return page;
    }
    
}
