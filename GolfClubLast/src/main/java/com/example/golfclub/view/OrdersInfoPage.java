/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.view;

import com.example.golfclub.entity.Orders;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author max19
 */
public class OrdersInfoPage extends HTMLPage{
    
    public OrdersInfoPage(String filepath) throws IOException {
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
    
    public void addOrder(Orders order){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        page = page.replaceFirst(
            "<!-- Orders section -->", 
            "<section>\n" +
                "<section>\n" +
                "<div class=\"container\">\n" +
                    "<div align = \"center\" class = \"col-lg-12\">" +
                        "<form method=\"POST\">\n" +
                            "<input type=\"text\" name=\"id\" readonly value=\"" + order.getIdOrder() + "\">\n" +
                            "<input type=\"text\" name=\"login\" readonly value=\"" + order.getIdClient().getLogin() + "\">\n" +
                            "<br/>" +
                            "<input type=\"text\" name=\"fieldName\" readonly value=\"" + order.getIdField().getFieldName() + "\">\n" +
                            "<input type=\"text\" name=\"leaseStart\" value=\"" + format.format(order.getLeaseStart()) + "\">\n" +
                            "<br/>" +
                            "<input type=\"text\" name=\"leaseEnd\" value=\"" + format.format(order.getLeaseEnd()) + "\">\n" +
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
            "<!-- Orders section -->\n"
        );
    }
}
