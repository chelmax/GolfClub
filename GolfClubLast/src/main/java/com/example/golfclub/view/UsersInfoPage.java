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
                "<div class=\"row btmpadding\">\n" +
                "<div class=\"col-lg-4 text-center\"></div>\n" +
                "<div class=\"col-lg-4\">\n" +
                "<div id=\"reg\">\n" +
                "<div class=\"inside\">\n" +
                "<div class=\"userFields\">\n" +
                "<div class=\"userField\">\n" +
                    "<form method=\"POST\">\n" +
                        "<input type=\"text\" name=\"login\" value=\"" + login + "\" readonly=\"\">\n" +
                        "<input type=\"text\" name=\"name\" value=\"" + name + "\">\n" +
                        "<input type=\"text\" name=\"email\" value=\"" + email + "\">\n" +
                        "<input type=\"text\" name=\"date\" value=\"" + format.format(birthDate) + "\">\n" +
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
            "<!-- Users section -->\n"
        );
    }
}
