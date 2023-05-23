/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu001844.framework;

import java.io.InputStream;
/**
 *
 * @author tonymushah
 */
public class FileUpload {
    private String name;
    private String path;
    private InputStream inputStream;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public FileUpload(String name, InputStream inputStream) {
        this.name = name;
        this.inputStream = inputStream;
    }

    public FileUpload() {
    }

    
    
}
