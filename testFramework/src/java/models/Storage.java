/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import etu001844.framework.bind.annotations.RequestMapping;
import etu001844.framework.bind.annotations.Scope;
import etu001844.framework.bind.enums.ScopeValue;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tonymushah
 */
@Scope(ScopeValue.Singleton)
public class Storage {
    private Set<Employee> employees;

    public Storage() {
        HashSet<Employee> emps = new HashSet();
        emps.add(new Employee("Jean", "Yofddsa"));
        emps.add(new Employee("Jean2", "Yofddsa3"));
        emps.add(new Employee("Jean4", "Yof"));
        this.setEmployees(emps);
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    @RequestMapping(url = "/storage/emps")
    public Set<Employee> getEmployees(HttpServletRequest request){
        return this.getEmployees();
    }
}
