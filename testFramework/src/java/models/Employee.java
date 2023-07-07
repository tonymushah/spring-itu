package models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import etu001844.framework.ModelView;
import etu001844.framework.bind.annotations.JSONResponse;
import etu001844.framework.bind.annotations.RequestMapping;
import etu001844.framework.bind.annotations.SkipMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    @SkipMapping
    private String dsdsd;
    
    public String getDsdsd() {
        return dsdsd;
    }

    public void setDsdsd(String dsdsd) {
        this.dsdsd = dsdsd;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee() {
    }

    public Employee(String name, String dsdsd) {
        this.setName(name);
        this.setDsdsd(dsdsd);
    }
    
    @RequestMapping(url = "/insert_emp")
    public ModelView getSubmited(@MethodParam(name = "dsdsd") String name){
        System.out.println(name);
        ModelView mv = new ModelView("/display_emp.jsp");
        mv.put("data", this);
        return mv;
    }
    @RequestMapping(url = "/get_emps")
    public Set<Employee> getStorage(Storage storage, HttpServletRequest request, HttpSession session, @MethodParam(name = "dsdsd") String name, @MethodParam(name = "dsds") String name_, HttpServletResponse response){
        response.addHeader("x-redirect", "true");
        if(storage == null){
            System.out.println("Storage is null");
        }
        if(request == null){
            System.out.println("request is null");
        }
        return storage.getEmployees();
    }
    @RequestMapping(url = "/json")
    @JsonIgnore
    @JSONResponse
    public ModelView getSubmited(){
        ModelView mv = new ModelView();
        List<Employee> list = new ArrayList();
        list.add(new Employee("dasdas", "dadasdasdsadsa"));
        list.add(new Employee());
        list.add(new Employee("Rad", "dar"));
        mv.put("data", list);
        mv.put("context", 90.f);
        mv.setUrl("/index.jsp");
        return mv;
    }
}
