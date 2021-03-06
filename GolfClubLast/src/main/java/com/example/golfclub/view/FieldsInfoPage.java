/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.view;

import com.example.golfclub.entity.Field;
import java.io.IOException;

/**
 *
 * @author max19
 */
public class FieldsInfoPage extends HTMLPage{
    
    public FieldsInfoPage(String filepath) throws IOException {
        super(filepath);
    }
    
    public void wrongEditInput(){
        page = page.replaceFirst(
                        "<section>", 
                        "<div>\n" +
                            "<div class=\"col-lg-12 text-center\">\n" +
                            "<h2 class=\"section-heading\">Wrong input!</h2>\n" +
                        "</div>" +
                        "<section>\n"
                );
    }
    
    public void addField(Field field){
        page = page.replaceFirst(
            "<!-- Fields section -->", 
            "<section>\n" +
                "<div class=\"container\">\n" +
                    "<div align = \"center\" class = \"col-lg-12\">" +
                        "<form method=\"POST\">\n" +
                            "<input type=\"text\" name=\"fieldName\" readonly value=\"" + field.getFieldName() + "\">\n" +
                            "<input type=\"text\" name=\"ownerName\" readonly value=\" " + field.getIdOwner().getName() + "\">\n" +
                            "<br/>" +
                            "<input type=\"text\" name=\"categoryType\" readonly value=\"" + field.getIdCategory().getType()+ "\">\n" +
                            "<input type=\"text\" name=\"size\" value=\"" + field.getSize() + "\">\n" +
                            "<br/>" +
                            "<input type=\"text\" name=\"pricePerDay\" value=\"" + field.getPricePerDay() + "\">\n" +
                            "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
                            "<input id = \"delete\" type=\"radio\" name=\"choose\" value=\"Delete\">\n" +
                            "<label>Delete&nbsp.</label>\n" +
                            "<input id = \"change\" type=\"radio\" name=\"choose\" value=\"Change\">\n" +
                            "<label>Change&nbsp</label>\n" +
                            "<br/>" +
                            "<input type=\"submit\" name=\"request\" value=\"Execute\">\n" +
                        "</form>\n" +
                    "</div>\n" +
                "</div>\n" +
            "</section>\n" +
            "<!-- Fields section -->\n"
        );
    }
}
