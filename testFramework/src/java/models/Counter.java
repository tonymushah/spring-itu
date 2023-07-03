/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import etu001844.framework.ModelView;
import etu001844.framework.bind.annotations.RequestMapping;
import etu001844.framework.bind.annotations.Scope;
import etu001844.framework.bind.annotations.SkipMapping;
import etu001844.framework.bind.enums.ScopeValue;
import mg.tonymushah.utils.bind.annotation.MethodParam;

/**
 *
 * @author tonymushah
 */
@Scope(ScopeValue.Singleton)
public class Counter {
    @SkipMapping
    private int number;
    @SkipMapping
    private int lastIncrement;
    @SkipMapping
    private int lastDecrement;

    public int getLastIncrement() {
        return lastIncrement;
    }

    public void setLastIncrement(int lastIncrement) {
        this.lastIncrement = lastIncrement;
    }

    public int getLastDecrement() {
        return lastDecrement;
    }

    public void setLastDecrement(int lastDecrement) {
        this.lastDecrement = lastDecrement;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    @RequestMapping(url = "/counter/increment")
    public ModelView increment(@MethodParam(name = "number") int number){
        if(number == 0){
            number = 1;
        }else{
            number = Math.abs(number);
        }
        this.setNumber(this.getNumber() + number);
        this.setLastIncrement(number);
        ModelView mv = new ModelView("/counter.jsp");
        mv.put("data", this);
        return mv;
    }
    @RequestMapping(url = "/counter/decrement")
    public ModelView decrement(@MethodParam(name = "number") int number){
        if(number == 0){
            number = 1;
        }else{
            number = Math.abs(number);
        }
        this.setNumber(this.getNumber() - number);
        this.setLastDecrement(number);
        ModelView mv = new ModelView("/counter.jsp");
        mv.put("data", this);
        return mv;
    }
    @RequestMapping(url = "/counter")
    public ModelView reset(){
        this.setNumber(0);
        ModelView mv = new ModelView("/counter.jsp");
        mv.put("data", this);
        return mv;
    }
}
