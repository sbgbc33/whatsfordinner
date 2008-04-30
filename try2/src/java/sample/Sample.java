/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.wfd.model.House;
import org.wfd.util.HibernateUtil;

/**
 *
 * @author tejash
 */
public class Sample {
    
    public static List getMeals() {
        SessionFactory session = HibernateUtil.getSessionFactory();
        return session.openSession().createQuery("from Meal").list();
//        return session.openSession().createQuery("Select h from Meal as h").list();
    }
    
    public static House getHouse(String houseName) {
        SessionFactory session = HibernateUtil.getSessionFactory();
        
        System.out.println("looking for " + houseName);
        House h = new House();
        h.setName(houseName);
        
        Session s = session.openSession();
        Criteria c = s.createCriteria(House.class);
        List l = c.add(Restrictions.eq("name", houseName)).list();
        
        
        if ( l != null && ! l.isEmpty() ) {
        	return (House)l.get(0);
        }
        
        return null;
    }
    
    public static void main(String args[]) {
        getMeals();
    }

}
