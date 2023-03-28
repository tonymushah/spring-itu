/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu001844.framework;

import java.lang.reflect.InvocationTargetException;
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

    public Mapping(Class<? extends Object> mappedClass, Method mappedMethod) {
        this.setMappedClass(mappedClass);
        this.setMappedMethod(mappedMethod);
    }

    public Mapping() {
    }
    public Object invokeMethod() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
        Object mappedClassInstance = this.mappedClass.getConstructor(null).newInstance(null);
        return this.mappedMethod.invoke(mappedClassInstance, null);
    }

    @Override
    public String toString() {
        return "(" + this.mappedClass.getName() + "," + this.mappedMethod.getName() + ")";
    }
    
}
