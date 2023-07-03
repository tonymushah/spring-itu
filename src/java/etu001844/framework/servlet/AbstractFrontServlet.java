/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu001844.framework.servlet;

import etu001844.framework.Mapping;
import etu001844.framework.ModelView;
import etu001844.framework.bind.annotations.CreateIfNull;
import etu001844.framework.bind.annotations.Scope;
import etu001844.framework.bind.annotations.SkipMapping;
import etu001844.framework.bind.enums.ScopeValue;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mg.tonymushah.utils.TCeutils;
import mg.tonymushah.utils.bind.annotation.MethodParam;
import mg.tonymushah.utils.enums.PrimaryClasses;

/**
 *
 * @author tonymushah
 */
public abstract class AbstractFrontServlet extends HttpServlet {

    protected HashMap<Class<?>, Object> singletons_object;

    public HashMap<Class<?>, Object> getSingletons_object() {
        return singletons_object;
    }

    public void setSingletons_object(HashMap<Class<?>, Object> singletons_object) {
        this.singletons_object = singletons_object;
    }
    public boolean isSingleton_via_annotation(Class<?> class_){
        Scope class_scope = class_.getAnnotation(Scope.class);
        if(class_scope != null){
            return class_scope.value() == ScopeValue.Singleton;
        }else{
            return false;
        }
    }
    public Set<Class<?>> search_singleton_classes(Set<Class<?>> class_set){
        HashSet<Class<?>> returns = new HashSet();
        for(Class<?> class_ : class_set){
            if(this.isSingleton_via_annotation(class_)) returns.add(class_);
        }
        return returns;
    }
    private boolean isClassInSingletonMapping(Class<?> class_){
        if(this.isSingleton_via_annotation(class_)){
            return this.singletons_object.get(class_) != null;
        }else{
            return false;
        }
    }
    private boolean isClassInSingletonMapping(Class<?> class_, HashMap<Class<?>, Object> storage){
        if(this.isSingleton_via_annotation(class_)){
            return storage.get(class_) != null;
        }else{
            return false;
        }
    }
    public void init_singleton_set(Set<Class<?>> classes, HashMap<Class<?>, Object> storage) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        for(Class<?> class_ : classes){
            if(this.isSingleton_via_annotation(class_)){
                storage.put(class_, class_.getConstructor().newInstance());
            }
        }
    }
    
    public void init_singletons() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        HashMap<Class<?>, Object> storage = new HashMap();
        this.init_singleton_set(this.findAllClasses(), storage);
        this.setSingletons_object(storage);
    }
    private HashMap<String, Parameter> get_method_param(Method to_use) {
        HashMap<String, Parameter> result = new HashMap();
        for (Parameter param : to_use.getParameters()) {
            MethodParam p = param.getDeclaredAnnotation(MethodParam.class);
            if (p != null) {
                result.put(p.name(), param);
            } else {
                result.put(param.getName(), param);
            }
        }
        return result;
    }
    private HashMap<String, Mapping> mappingURLs;

    public HashMap<String, Mapping> getMappingURLs() {
        return mappingURLs;
    }

    public void setMappingURLs(HashMap<String, Mapping> mappingURLs) {
        this.mappingURLs = mappingURLs;
    }

    public Set<File> getAllClassFilesInDir(File directory) {
        HashSet<File> class_files = new HashSet<File>();
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".class") == true) {
                class_files.add(file);
            } else if (file.isDirectory() == true) {
                class_files.addAll(this.getAllClassFilesInDir(file));
            }
        }
        return class_files;
    }

    public Class<?> extract_class_from_file(File classFile, File root) throws ClassNotFoundException {
        ClassLoader loader = this.getServletContext().getClassLoader();
        String rootPackage = root.getAbsolutePath();
        String packageName = classFile.getParentFile().getAbsolutePath().replaceFirst(rootPackage, "").replaceAll("/", ".");
        String className = classFile.getName().replaceAll(".class", "").replaceAll("/", ".");
        if (packageName.startsWith(".")) {
            packageName = packageName.replaceFirst("[.]", "");
        }
        if (packageName.isBlank()) {
            String to_load = String.format("%s", className);
            System.out.println(to_load);
            return loader.loadClass(to_load);
        } else {
            String to_load = String.format("%s.%s", packageName, className);
            System.out.println(to_load);
            return loader.loadClass(to_load);
        }
    }

    public Set<File> getAllClassFiles(File root) throws ClassNotFoundException {
        Set<File> files = this.getAllClassFilesInDir(root);
        return files;
    }

    public Set<Class<?>> findAllClasses() throws ClassNotFoundException {
        File root = new File(this.getServletContext().getClassLoader().getResource("/").getFile());
        HashSet<Class<?>> returns = new HashSet<Class<?>>();
        for (File file : this.getAllClassFiles(root)) {
            returns.add(this.extract_class_from_file(file, root));
        }
        return returns;
    }

    public Mapping getRequestMapping(HttpServletRequest request) throws ServletException {
        String pathInfo = request.getServletPath();
        if (pathInfo != null) {
            return this.getMappingURLs().get(pathInfo.replaceFirst(".do", ""));
        } else {
            throw new ServletException(String.format("Method not found for url %s", pathInfo));
        }

    }
    
    protected Object get_or_init_object(Mapping to_use) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        if(this.isClassInSingletonMapping(to_use.getMappedClass())){
            return this.singletons_object.get(to_use.getMappedClass());
        }else{
            return to_use.getMappedClass().getConstructor().newInstance();
        }
    }
    
    public Object init_mapped_class(HttpServletRequest request, Mapping to_use) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object instance = this.get_or_init_object(to_use);
        TCeutils instance_u = new TCeutils(instance);
        Enumeration<String> attributeNames = request.getAttributeNames();
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            try {
                if(instance_u.getFields()[instance_u.getFieldIndex(param.getKey())].isAnnotationPresent(SkipMapping.class) == false){
                    if (instance_u.getFields()[(instance_u.getFieldIndex(param.getKey()))].getType() == String.class.arrayType()) {
                        instance_u.setInField(param.getKey(), param.getValue());
                    } else {
                        instance_u.setInField(param.getKey(), param.getValue()[0]);
                    }
                }
            } catch (NoSuchFieldError e) {
                System.out.println(e.getMessage());
            }
        }
        return instance;
    }

    // TODO Test 
    public ModelView invoke_method(Object instance, Method mappedMethod, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
        HashMap<Parameter, Object> parameterValue = new HashMap();
        HashMap<String, Parameter> methodParam = this.get_method_param(mappedMethod);
        for (Map.Entry<String, Parameter> param : methodParam.entrySet()) {
            System.out.println(param.getKey());
            String[] request_parameter_value = request.getParameterValues(param.getKey());
            if (request_parameter_value != null) {
                if (request_parameter_value.length == 1) {
                    PrimaryClasses primaryClass = PrimaryClasses.getPrimaryClass(param.getValue().getType());
                    if (primaryClass != null) {
                        switch (primaryClass) {
                            case Integer:
                                parameterValue.put(param.getValue(), Integer.valueOf(request_parameter_value[0]).intValue());
                                break;
                            case Boolean:
                                parameterValue.put(param.getValue(), Boolean.valueOf(request_parameter_value[0]).booleanValue());
                                break;
                            case Float:
                                parameterValue.put(param.getValue(), Float.valueOf(request_parameter_value[0]).floatValue());
                                break;
                            case Double:
                                parameterValue.put(param.getValue(), Double.valueOf(request_parameter_value[0]).doubleValue());
                                break;
                            case Long:
                                parameterValue.put(param.getValue(), Long.valueOf(request_parameter_value[0]).longValue());
                                break;
                            case Short:
                                parameterValue.put(param.getValue(), Short.valueOf(request_parameter_value[0]).shortValue());
                                break;
                            default:
                                parameterValue.put(param.getValue(), request_parameter_value[0]);
                                break;
                        }
                    }else{
                        parameterValue.put(param.getValue(), request_parameter_value[0]);
                    }
                } else {
                    if (param.getValue().getType() == String.class.arrayType()) {
                        parameterValue.put(param.getValue(), request_parameter_value);
                    }else if(param.getValue().getType() == HttpSession.class){
                        if(param.getValue().getType().isAnnotationPresent(CreateIfNull.class)){
                            parameterValue.put(param.getValue(), request.getSession(true));
                        }else{
                            parameterValue.put(param.getValue(), request.getSession());
                        }
                    }
                }
            } else {
                if (param.getValue().getType().isPrimitive()) {
                    parameterValue.put(param.getValue(), 0);
                } else {
                    parameterValue.put(param.getValue(), null);
                }
            }
        }
        return (ModelView) mappedMethod.invoke(instance, parameterValue.values().toArray());
    }

    public ModelView get_model_view(HttpServletRequest request) throws ServletException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Mapping to_use = this.getRequestMapping(request);
        Object instance = this.init_mapped_class(request, to_use);
        return this.invoke_method(instance, to_use.getMappedMethod(), request);
        //return (ModelView) to_use.getMappedMethod().invoke(instance);
    }

    @Override
    public void init() throws ServletException {
        try {
            this.init_singletons();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractFrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AbstractFrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AbstractFrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractFrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractFrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AbstractFrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

}
