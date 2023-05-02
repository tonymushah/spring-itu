/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu001844.framework.servlet;

import etu001844.framework.Mapping;
import etu001844.framework.ModelView;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import mg.tonymushah.utils.TCeutils;
import mg.tonymushah.utils.TUtils;
import mg.tonymushah.utils.enums.PrimaryClasses;

/**
 *
 * @author tonymushah
 */
public abstract class AbstractFrontServlet extends HttpServlet {

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

    public Object init_mapped_class(HttpServletRequest request, Mapping to_use) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object instance = to_use.getMappedClass().getConstructor().newInstance();
        TCeutils instance_u = new TCeutils(instance);
        Enumeration<String> attributeNames = request.getAttributeNames();
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            System.out.println(String.format("%s : %s", param.getKey(), param.getValue()[0]));
            try {
                if (instance_u.getFields()[(instance_u.getFieldIndex(param.getKey()))].getType() == String.class.arrayType()) {
                    instance_u.setInField(param.getKey(), param.getValue());
                } else {
                    instance_u.setInField(param.getKey(), param.getValue()[0]);
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
        for (Parameter param : mappedMethod.getParameters()) {
            String[] request_parameter_value = request.getParameterValues(param.getName());
            if (request_parameter_value != null) {
                if (request_parameter_value.length == 1) {
                    switch (PrimaryClasses.getPrimaryClass(param.getType())) {
                        case Integer:
                            parameterValue.put(param, Integer.valueOf(request_parameter_value[0]).intValue());
                            break;
                        case Boolean:
                            parameterValue.put(param, Boolean.valueOf(request_parameter_value[0]).booleanValue());
                            break;
                        case Float:
                            parameterValue.put(param, Float.valueOf(request_parameter_value[0]).floatValue());
                            break;
                        case Double:
                            parameterValue.put(param, Double.valueOf(request_parameter_value[0]).doubleValue());
                            break;
                        case Long:
                            parameterValue.put(param, Long.valueOf(request_parameter_value[0]).longValue());
                            break;
                        case Short:
                            parameterValue.put(param, Short.valueOf(request_parameter_value[0]).shortValue());
                            break;
                        default:

                            break;
                    }

                } else {
                    if (param.getType() == String.class.arrayType()) {
                        parameterValue.put(param, request_parameter_value);
                    }
                }
            }else{
                if(param.getType().isPrimitive()){
                    parameterValue.put(param, 0);
                }else{
                    parameterValue.put(param, null);
                }
            }
        }
        return (ModelView) mappedMethod.invoke(instance, parameterValue.values().toArray());
    }

    public ModelView get_model_view(HttpServletRequest request) throws ServletException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Mapping to_use = this.getRequestMapping(request);
        Object instance = this.init_mapped_class(request, to_use);

        return (ModelView) to_use.getMappedMethod().invoke(instance);
    }
}
