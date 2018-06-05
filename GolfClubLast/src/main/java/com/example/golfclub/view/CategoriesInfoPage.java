/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author max19
 */
public class CategoriesInfoPage extends HTMLPage{
    
    public CategoriesInfoPage(String filepath) throws IOException {
        super(filepath);
    }
    
    public void addCategory(String type){
        page = page.replaceFirst(
            "<!-- Сategories section -->", 
            "<section>\n" +
                "<div class=\"container\">\n" +
                    "<div align = \"center\" class = \"col-lg-12\">" +
                        "<form method=\"POST\">\n" +
                            "<input type=\"text\" name=\"type\" value=\"" + type + "\">\n" +
                            "<input type=\"submit\" name=\"choose\" value=\"Delete\">" +
                        "</form>\n" +
                    "</div>\n" +
                "</div>\n" +
            "</section>\n" +
            "<!-- Сategories section -->"
        );
    }
}
