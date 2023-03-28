/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu001844.framework.bind.annotations;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import mg.tonymushah.utils.AccessingAllClassesInPackage;
import mg.tonymushah.utils.exceptions.PackageNotFoundException;

/**
 *
 * @author tonymushah
 */
public abstract class AbstractFrontServlet extends HttpServlet {
    public Set<File> getAllClassFilesInDir(File directory){
        HashSet<File> class_files = new HashSet<File>();
        for(File file : directory.listFiles()){
            if(file.getName().endsWith(".class") == true){
                class_files.add(file);
            }else if (file.isDirectory() == true){
                class_files.addAll(this.getAllClassFilesInDir(file));
            }
        }
        return class_files;
    }
    public Class<?> extract_class_from_file(File classFile, File root) throws ClassNotFoundException{
        ClassLoader loader = this.getServletContext().getClassLoader();
        String rootPackage = root.getAbsolutePath();
        String packageName = classFile.getParentFile().getAbsolutePath().replaceFirst(rootPackage, "").replaceAll("/", ".");
        String className = classFile.getName().replaceAll(".class", "").replaceAll("/", ".");
        if(packageName.startsWith(".")){
            packageName = packageName.replaceFirst("[.]", "");
        }
        if(packageName.isBlank()){
            String to_load = String.format("%s",className);
            System.out.println(to_load);
            return loader.loadClass(to_load);
        }else{
            String to_load = String.format("%s.%s", packageName, className);
            System.out.println(to_load);
            return loader.loadClass(to_load);
        }
    }
    public Set<File> getAllClassFiles(File root) throws ClassNotFoundException{
        Set<File> files = this.getAllClassFilesInDir(root);
        return files;
    }
    public Set<Class<?>> findAllClasses() throws ClassNotFoundException{
        File root = new File(this.getServletContext().getClassLoader().getResource("/").getFile());
        HashSet<Class<?>> returns = new HashSet<Class<?>>();
        for(File file : this.getAllClassFiles(root)){
            returns.add(this.extract_class_from_file(file, root));
        }
        return returns;
    }
}
