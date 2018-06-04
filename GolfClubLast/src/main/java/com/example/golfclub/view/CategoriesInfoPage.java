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
    
    public CategoriesInfoPage(String filepath) throws UnsupportedEncodingException, IOException {
        super(filepath);
    }
    
    public void addCategory(String type){
        page = page.replaceFirst(
            "<!-- Сategories section -->", 
            "<section>\n" +
                "<div class=\"row btmpadding\">\n" +
                "<div class=\"col-lg-4 text-center\"></div>\n" +
                "<div class=\"col-lg-4\">\n" +
                "<div id=\"reg\">\n" +
                "<div class=\"inside\">\n" +
                "<div class=\"userFields\">\n" +
                "<div class=\"userField\">\n" +
                    "<form method=\"POST\">\n" +
                        "<input type=\"text\" name=\"type\" value=\"" + type + "\">\n" +
                        "<input type=\"submit\" name=\"choose\" value=\"Delete\">" +
                    "</form>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"col-lg-4 text-center\"></div>\n" +
                "</div>\n" +
            "</section>\n" +
            "<!-- Сategories section -->"
        );
    }
}
