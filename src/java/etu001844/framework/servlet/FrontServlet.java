/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu001844.framework.servlet;

import controllers.NewClass;
import etu001844.framework.Mapping;
import etu001844.framework.bind.annotations.AbstractFrontServlet;
import etu001844.framework.bind.annotations.Controller;
import etu001844.framework.bind.annotations.RequestMapping;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mg.tonymushah.utils.AccessingAllClassesInPackage;
import mg.tonymushah.utils.ClassUtils;
import mg.tonymushah.utils.exceptions.PackageNotFoundException;
import org.reflections.Reflections;

/**
 *
 * @author tonymushah
 */
@WebServlet(name = "FrontServlet", urlPatterns = {"/*"})
public class FrontServlet extends AbstractFrontServlet {
    private HashMap<String, Mapping> mappingURLs;

    public HashMap<String, Mapping> getMappingURLs() {
        return mappingURLs;
    }

    public void setMappingURLs(HashMap<String, Mapping> mappingURLs) {
        this.mappingURLs = mappingURLs;
    }

    @Override
    public void init() throws ServletException {
        if (this.mappingURLs == null) {
            this.setMappingURLs(new HashMap<String, Mapping>());
        }
        System.out.println(this.getServletContext().getClassLoader().resources("controllers").toList().size());
        try {
            HashMap<Method, ArrayList<RequestMapping>> methods;
            methods = ClassUtils.findAllMethodOfPackageByClassAnnotation(this.findAllClasses(), RequestMapping.class);
            System.out.println(methods.size());
            for (Map.Entry<Method, ArrayList<RequestMapping>> entry : methods.entrySet()) {
                String url = entry.getValue().get(0).url();
                Class<?> mappingClass = entry.getKey().getDeclaringClass();
                if (mappingClass.isAnnotationPresent(Controller.class)) {
                    url = mappingClass.getDeclaredAnnotation(Controller.class).url() + url;
                }
                Mapping mappingUrl = new Mapping(mappingClass, entry.getKey());
                this.mappingURLs.put(url, mappingUrl);
            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex.getMessage(), ex.getCause());  
        }   
        super.init();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FrontServlet at " + request.getContextPath() + "</h1>");
            out.println("<ul>");
            for (Map.Entry<String, Mapping> urls : this.mappingURLs.entrySet()) {
                out.println(String.format("<li>%s : %s</li>", urls.getKey(), urls.getValue().toString()));
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
