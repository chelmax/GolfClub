/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

/**
 *
 * @author max19
 */
public class CategoriesInfoPage extends HTMLPage{
    
    public CategoriesInfoPage(String filepath) throws UnsupportedEncodingException, IOException {
        super(filepath);
    }
    
    public void addCategory(String type){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
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
                        "<input type=\"submit\" name=\"request\" value=\"Выполнить\">\n" +
                        "<br/>" +
                        "<input id = \"delete\" type=\"radio\" name=\"choose\" value=\"Удалить\">\n" +
                        "<label for=\"del\">Удалить</label>\n" +
                        "<input id = \"change\" type=\"radio\" name=\"choose\" value=\"Изменить\">\n" +
                        "<label for=\"change\">Изменить</label>\n" +
                    "</form>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"col-lg-4 text-center\"></div>\n" +
                "</div>\n" +
            "</section>\n" +
            "<!-- Сategories section -->\n"
        );
    }
}
