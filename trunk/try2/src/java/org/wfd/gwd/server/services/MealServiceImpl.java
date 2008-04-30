/*
 * MealServiceImpl.java
 *
 * Created on April 27, 2008, 10:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.wfd.gwd.server.services;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.List;
import org.wfd.gwd.client.services.MealService;
import org.wfd.model.Meal;
import sample.Sample;

/**
 *
 * @author tejash
 */
public class MealServiceImpl extends RemoteServiceServlet implements
    MealService {

    public String getMeals2(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }
    
    public String getMeals(String s) {
        List l = Sample.getMeals();
        
                
        StringBuilder sb = new StringBuilder();
        for(Object o : l) {
            Meal m = (Meal) o;
            sb.append(m.getId());
            sb.append(" ");
            sb.append(m.getName());
            sb.append(" ");
            sb.append(m.getBldType());
            sb.append(" ");
            sb.append("\n");
        }
        return sb.toString();
//
//        if ( l == null || l.isEmpty() ) {
//            return "0 meals";
//        } else { 
//            return l.size() + " meals";
//        }
    }
}
