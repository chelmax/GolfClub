/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author max19
 */
public class HTMLPage {
    
    protected String page;
    
    public HTMLPage(String filepath) 
            throws UnsupportedEncodingException, IOException{
        File f = new File(filepath);
        BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(f), "UTF8"));
        StringBuilder res = new StringBuilder();
        String nextString;
        while ((nextString = br.readLine()) != null) {
            res.append(nextString);
        }
        page = res.toString();
    }
    
    public void navBarForAuthorized(String username,Integer ordersCount){
        page = page.replaceFirst("href = \"./login\"", "href = \"./busket\"")
                    .replaceFirst("Log In", "Orders: " + ordersCount)
                    .replaceFirst("href = \"./signup\"", "")
                    .replaceFirst("Sign Up", username);
    }
    
    public void replaceFirst(String oldText, String newText){
        page = page.replaceFirst(oldText, newText);
    }
    
    @Override
    public String toString(){
        return page;
    }

    public void navBarForAdmin() {
        page = page.replaceFirst("href = \"./login\"", "href = \"./admin_users\"")
                            .replaceFirst("Log In", "Users")
                            .replaceFirst("href = \"./signup\"", "href = \"./admin_fields\"")
                            .replaceFirst("Sign Up", "Fields");    
    }
}
