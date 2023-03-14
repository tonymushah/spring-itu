/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu001844.framework;

import java.lang.reflect.Method;

/**
 *
 * @author tonymushah
 */
public class Mapping {
    private Class<? extends Object> mappedClass;
    private Method mappedMethod;

    public Class<? extends Object> getMappedClass() {
        return mappedClass;
    }

    public void setMappedClass(Class<? extends Object> mappedClass) {
        this.mappedClass = mappedClass;
    }

    public Method getMappedMethod() {
        return mappedMethod;
    }

    public void setMappedMethod(Method mappedMethod) {
        this.mappedMethod = mappedMethod;
    }
    
}
