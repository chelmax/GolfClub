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
public class MainPage extends HTMLPage{
    
    public MainPage(String filepath) 
            throws UnsupportedEncodingException, IOException {
        super(filepath);
    }
    
    public void addField(String fieldName, String categoryType, String ownerName, float size, int price, int iterator){
        addFieldSlide(fieldName);
        addFieldSection(fieldName, categoryType, iterator);
        addFieldModal(fieldName, categoryType, ownerName, size, price, iterator);
    }
    
    private void addFieldSlide(String fieldName){
        page = page.replaceFirst(
                "<!-- Java input fields slide here -->", 
                "<div>\n" +
                    "<img src=\"img/fields/" + fieldName + "-thumbnail.jpg\">\n" +
                "</div>\n" +
                "<!-- Java input fields slide here -->"
        );
    }
    
    private void addFieldSection(String fieldName, String categoryType, int iterator){
        page = page.replaceFirst(
            "<!-- Java input fields section here -->",
            "<div class=\"col-md-4 col-sm-6 fields-item\">\n" +
                "<a class=\"fields-link\" data-toggle=\"modal\" href=\"#fieldsModal" + iterator + "\">\n" +
                    "<div class=\"fields-hover\">\n" +
                        "<div class=\"fields-hover-content\">\n" +
                            "<i class=\"fa fa-plus fa-3x\"></i>\n" +
                        "</div>\n" +
                    "</div>\n" +
                    "<img class=\"img-fluid\" src=\"img/fields/" + fieldName + "-thumbnail.jpg\" alt=\"\">\n" +
                "</a>\n" +
                "<div class=\"fields-caption\">\n" +
                    "<h4>" + fieldName + "</h4>\n" +
                    "<p class=\"text-muted\">" + categoryType + "</p>\n" +
                "</div>\n" +
            "</div>" +
            "<!-- Java input fields section here -->"
        );
    }
    
    private void addFieldModal(String fieldName, String categoryType, String ownerName, float size, int price, int iterator){
        page = page.replaceFirst(
                "<!-- Java input fields modals here -->", 
                "<div class=\"fields-modal modal fade\" id=\"fieldsModal" + iterator + "\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\">\n" +
                    "<div class=\"modal-dialog\">\n" +
                        "<div class=\"modal-content\">\n" +
                            "<div class=\"close-modal\" data-dismiss=\"modal\">\n" +
                                "<div class=\"lr\">\n" +
                                    "<div class=\"rl\"></div>\n" +
                                "</div>\n" +
                            "</div>\n" +
                            "<div class=\"container\">\n" +
                                "<div class=\"row\">\n" +
                                    "<div class=\"col-lg-8 mx-auto\">\n" +
                                        "<div class=\"modal-body\">\n" +
                                            "<!-- Project Details Go Here -->\n" +
                                            "<h2>" + fieldName + "</h2>\n" +
                                            "<p class=\"item-intro text-muted\">" + categoryType + " field</p>\n" +
                                            "<img class=\"img-fluid d-block mx-auto\" src=\"img/fields/" + fieldName + "-full.jpg\" alt=\"\">\n" +
                                            "<p>" +
                                                "This field is  " + ownerName + "'s one. There're some characteristics of it. <br/>" +
                                                "Size: " + size + "<br/>" +
                                                "Price per day: " + price + "<br/>" +
                                            "</p>\n" +
                                            "<button class=\"btn btn-primary\" data-dismiss=\"modal\" type=\"button\">\n" +
                                            "<i class=\"fa fa-times\"></i>\n" +
                                            "Close Project</button>\n" +
                                        "</div>\n" +
                                    "</div>\n" +
                                "</div>\n" +
                            "</div>\n" +
                        "</div>\n" +
                    "</div>\n" +
                "</div>" +
                "<!-- Java input fields modals here -->"
        );
    }
    
}
