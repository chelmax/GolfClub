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
public class UsersBusketPage extends HTMLPage{
    
    public UsersBusketPage(String filepath) throws UnsupportedEncodingException, IOException {
        super(filepath);
    }
    
    public void orderButton(){
        page = page.replaceFirst(
                "<!-- Button here -->",
                "<button class=\"btn btn-primary\" type=\"submit\" formmethod=\"POST\">\n" +
                    "Order fields\n" +
                "</button>\n"
        );
    }
    
    public void addOrderInfo(String fieldName, String leaseStart, String leaseEnd){
        page = page.replaceFirst(
            "<!-- Field's order info here -->", 
            "<tr>" +
                "<td class=\"text-center\">" + fieldName + "</td>" +
                "<td class=\"text-center\">" + leaseStart + "</td>" +
                "<td class=\"text-center\">" + leaseEnd + "</td>" +
                "<td class=\"text-center\">" +
                    "<form  onSubmit=\"return send(this)\">" +
                        "<button name = \"nameButton\" class=\"btn btn-primary\" " + 
                        "type=\"submit\" value=\"" + fieldName + "\">\n" +
                            "Delete\n" +
                        "</button>\n" +
                    "</form>\n" +
                "</td>" +
            "</tr>" +
            "<!-- Field's order info here -->"
        );
    }

    public void emptyBusket() {
        page = page.replaceFirst(
            "<!-- Field's order info here -->", 
            "<caption class=\"text-center\">Your busket is empty</caption>"
        );
        page = page.replaceFirst(
            "<th class=\"text-center\"></th>", 
            ""
        );
    }
    
}
