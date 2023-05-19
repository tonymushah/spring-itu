package models;


import etu001844.framework.ModelView;
import etu001844.framework.bind.annotations.RequestMapping;
import mg.tonymushah.utils.bind.annotation.MethodParam;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tonymushah
 */
public class Employee {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @RequestMapping(url = "/insert_emp")
    public ModelView getSubmited(@MethodParam(name = "name") String name){
        System.out.println(name);
        ModelView mv = new ModelView("/display_emp.jsp");
        mv.put("data", this);
        return mv;
    }
}
