/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import etu001844.framework.bind.annotations.RequestMapping;

/**
 *
 * @author tonymushah
 */
public class NewClass {
    @RequestMapping(url = "new-class/find_all")
    public String findAll(){
        return "tsisy";
    }
    @RequestMapping(url = "new-class/find_one")
    public String findAone(){
        return "tsisy";
    }
}
