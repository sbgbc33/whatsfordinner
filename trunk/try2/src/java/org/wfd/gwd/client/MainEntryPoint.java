/*
 * MainEntryPoint.java
 *
 * Created on April 27, 2008, 7:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.wfd.gwd.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.wfd.gwd.client.services.MealServiceUsageExample;

/**
 *
 * @author tejash
 */
public class MainEntryPoint implements EntryPoint {

    /** Creates a new instance of MainEntryPoint */
    public MainEntryPoint() {
    }

    /** 
        The entry point method, called automatically by loading a module
        that declares an implementing class as an entry-point
    */
    public void onModuleLoad() {
//        final Label label = new Label("Hello, GWT!!!");
//        final Button button = new Button("Click me!");
//        
//        final Button button2 = new Button("Click me2!");
//        button.addClickListener(new ClickListener(){
//            public void onClick(Widget w) {
//                label.setVisible(!label.isVisible());
//            }
//        });
//        
//        RootPanel.get().add(button);
//        RootPanel.get().add(button2);
//        RootPanel.get().add(label);
        
        RootPanel.get().add(new MealServiceUsageExample());
        
//        List meals = Sample.getMeals();
//        
//        if ( meals == null || meals.isEmpty() ) {
//            button.setText("Empty");
//        } else {
//            button.setText("Found " + meals.size() + " meals");
//        }
    }

}
