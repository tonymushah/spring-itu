
import etu001844.framework.ModelView;
import etu001844.framework.bind.annotations.RequestMapping;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tonymushah
 */
public class NewClass {
    @RequestMapping(url = "/emp-all")
    public ModelView emp(){
        return new ModelView("/newjsp.jsp");
    }
}
