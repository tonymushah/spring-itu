/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu001844.framework.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import etu001844.framework.Mapping;
import etu001844.framework.ModelView;
import etu001844.framework.bind.annotations.Controller;
import etu001844.framework.bind.annotations.RequestMapping;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mg.tonymushah.utils.ClassUtils;

/**
 *
 * @author tonymushah
 */
@WebServlet(name = "FrontServlet", urlPatterns = {"*.do"})
public class FrontServlet extends AbstractFrontServlet {

    protected ObjectMapper mapper;

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        if (this.getMappingURLs() == null) {
            this.setMappingURLs(new HashMap<String, Mapping>());
        }
        try {
            HashMap<Method, ArrayList<RequestMapping>> methods;
            methods = ClassUtils.findAllMethodOfPackageByClassAnnotation(this.findAllClasses(), RequestMapping.class);
            for (Map.Entry<Method, ArrayList<RequestMapping>> entry : methods.entrySet()) {
                for (RequestMapping requestMapping : entry.getValue()) {
                    String url = requestMapping.url();
                    Class<?> mappingClass = entry.getKey().getDeclaringClass();
                    if (mappingClass.isAnnotationPresent(Controller.class)) {
                        url = mappingClass.getDeclaredAnnotation(Controller.class).url() + url;
                    }
                    Mapping mappingUrl = new Mapping(mappingClass, entry.getKey());
                    this.getMappingURLs().put(url, mappingUrl);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex.getMessage(), ex.getCause());
        }
        this.setMapper(new ObjectMapper());
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
    protected void processModelView(ModelView to_use_of_to_use, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        if (to_use_of_to_use.getCookie() != null) {
            if (to_use_of_to_use.getCookie().isEmpty() == false) {
                for (Cookie cookie : to_use_of_to_use.getCookie()) {
                    response.addCookie(cookie);
                }
            }
        }
        if (to_use_of_to_use.getHeaders() != null) {
            if (to_use_of_to_use.getHeaders().isEmpty() == false) {
                for (Map.Entry<String, String> header : to_use_of_to_use.getHeaders().entrySet()) {
                    response.addHeader(header.getKey(), header.getValue());
                }
            }
        }
        if (to_use_of_to_use.isIsJson() && to_use_of_to_use.getUrl() != null) {
            HashMap<String, Object> data = to_use_of_to_use.getData();
            response.setContentType("application/json");
            this.getMapper().writeValue(response.getOutputStream(), data);
        }else if(to_use_of_to_use.isIsJson() && to_use_of_to_use.getUrl() == null){
            HashMap<String, Object> data = to_use_of_to_use.getData();
            response.setContentType("application/json");
            this.getMapper().writeValue(response.getOutputStream(), data);
        }else{
            for (Map.Entry<String, Object> data : to_use_of_to_use.entrySet()) {
                request.setAttribute(data.getKey(), data.getValue());
            }
            this.getServletContext().getRequestDispatcher(to_use_of_to_use.getUrl()).forward(request, response);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Object to_use = this.get(request, response);
            if (to_use instanceof ModelView) {
                ModelView mv = (ModelView) to_use;
                this.processModelView(mv, request, response);
            } else {
                response.setContentType("application/json");
                this.getMapper().writeValue(response.getOutputStream(), to_use);
            }

        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public long getLastModified(HttpServletRequest req) {
        return super.getLastModified(req); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
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
