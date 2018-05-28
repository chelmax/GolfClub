/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.view;

import com.example.golfclub.entity.Field;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

/**
 *
 * @author max19
 */
public class FieldsInfoPage extends HTMLPage{
    
    public FieldsInfoPage(String filepath) throws UnsupportedEncodingException, IOException {
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        page = page.replaceFirst(
            "<!-- Fields section -->", 
            "<section>\n" +
                "<div class=\"row btmpadding\">\n" +
                "<div class=\"col-lg-4 text-center\"></div>\n" +
                "<div class=\"col-lg-4\">\n" +
                "<div id=\"reg\">\n" +
                "<div class=\"inside\">\n" +
                "<div class=\"userFields\">\n" +
                "<div class=\"userField\">\n" +
                    "<form method=\"POST\">\n" +
                        "<input type=\"text\" name=\"fieldName\" value=\"" + field.getFieldName() + "\" readonly=\"\">\n" +
                        "<input type=\"text\" name=\"ownerName\" readonly value=\" " + field.getIdOwner().getName() + "\">\n" +
                        "<input type=\"text\" name=\"categoryType\" readonly value=\"" + field.getIdCategory().getType()+ "\">\n" +
                        "<input type=\"text\" name=\"size\" value=\"" + field.getSize() + "\">\n" +
                        "<input type=\"text\" name=\"pricePerDay\" value=\"" + field.getPricePerDay() + "\">\n" +
                        "<br/>" +
                        "<input type=\"submit\" name=\"request\" value=\"Выполнить\">\n" +
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
            "<!-- Fields section -->\n"
        );
    }
}
