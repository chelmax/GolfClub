/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author max19
 */
public class UsersInfoPage extends HTMLPage{
    
    public UsersInfoPage(String filepath) throws UnsupportedEncodingException, IOException {
        super(filepath);
    }
    
    public void wrongEditInput(){
        page = page.replaceFirst(
                        "<section>",
                        "<div>\n" +
                            "<div class=\"col-lg-12 text-center\">\n" +
                            "<h2 class=\"section-heading\">Wrong input!</h2>\n" +
                            "</div>" +
                        "</div>" +
                        "<section>\n"
                );
    }
    
    public void addUser(String name, String email, String login, Date birthDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        page = page.replaceFirst(
            "<!-- Users section -->", 
            "<section>\n" +
                "<div class=\"container\">\n" +
                    "<div align = \"center\" class = \"col-lg-12\">" +
                        "<form method=\"POST\">\n" +
                            "<input type=\"text\" name=\"login\" readonly value=\"" + login + "\" readonly>\n" +
                            "<input type=\"text\" name=\"name\" value=\"" + name + "\">\n" +
                            "<br/>" +
                            "<input type=\"text\" name=\"email\" value=\"" + email + "\">\n" +
                            "<input type=\"text\" name=\"date\" value=\"" + format.format(birthDate) + "\">\n" +
                            "<br/>" +
                            "<input id = \"delete\" type=\"radio\" name=\"choose\" value=\"Delete\">\n" +
                            "<label>Delete&nbsp.</label>\n" +
                            "<input id = \"change\" type=\"radio\" name=\"choose\" value=\"Change\">\n" +
                            "<label>Change&nbsp</label>\n" +
                            "<input type=\"submit\" name=\"request\" value=\"Execute\">\n" +
                        "</form>\n" +
                    "</div>\n" +
                "</div>\n" +
            "</section>\n" +
            "<!-- Users section -->\n"
        );
    }
}
