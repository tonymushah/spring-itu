
import etu001844.framework.ModelView;
import etu001844.framework.bind.annotations.RequestMapping;
import java.util.Arrays;

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
        ModelView mv = new ModelView("/newjsp.jsp");
        mv.put("noms", Arrays.asList("Tony", "Elon", "Jeff"));
        return mv;
    }
}
