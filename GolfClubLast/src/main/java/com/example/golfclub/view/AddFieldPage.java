/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.view;

import com.example.golfclub.entity.Category;
import com.example.golfclub.entity.Owner;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author max19
 */
public class AddFieldPage extends HTMLPage {
    
    public AddFieldPage(String filepath, List<Owner> owners, List<Category> categories) 
            throws IOException {
        super(filepath);
        owners.forEach(owner -> 
                setOwnerName(owner)
        );
        categories.forEach(category -> 
                setCategoryTypes(category)
        );
    }
    
    private void setOwnerName(Owner owner){
        page = page.replaceFirst(
                        "<!-- Owners' names here-->", 
                        "<option value=\"" + owner.getIdOwner() + "\">" 
                                + owner.getName() + 
                        "</option>" +
                        "<!-- Owners' names here-->\n"
                );
    }
    
    private void setCategoryTypes(Category category){
        page = page.replaceFirst(
                        "<!-- Categories here-->", 
                        "<option value=\"" + category.getIdCategory() + "\">" 
                                + category.getType() + 
                        "</option>" +
                        "<!-- Categories here-->\n"
                );
    }
    
}
