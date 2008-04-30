/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.wfd.util.HibernateUtil;

/**
 *
 * @author tejash
 */
public class TestMenu {
    
    private void load() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.getCurrentSession();
        List l = s.createQuery("select * from Meal").list();
        System.out.println("l.size() = " + l.size());
    }
    public static void main(String args[]) {
        TestMenu t = new TestMenu();
        t.load();
    }

}
