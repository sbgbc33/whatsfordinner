/*
 * GWTLoginServiceUsageExample.java
 *
 * Created on April 29, 2008, 9:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.wfd.gwd.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.wfd.gwd.client.services.MealServiceUsageExample;

/**
 * Example class using the GWTLoginService service. 
 *
 * @author tejash
 */
public class GWTMainPage extends VerticalPanel {
    private Label lblServerReply = new Label();
    private Button btnMeal = new Button("Manage Meals");
    private Button btnGenMenu = new Button("Generate Menu");
    private Button btnHouse = new Button("Manage Houses");
    private Button btnLogout = new Button("Logout");
    
    public GWTMainPage(Object o) {
        add( new Label("Welcome " + o));
        add(btnMeal);
        add(btnGenMenu);
        add(btnHouse);
        add(btnLogout);
        
        // Create an asynchronous callback to handle the result.
        final AsyncCallback callback = new AsyncCallback() {
            public void onSuccess(Object result) {
            	if ( result == null )
            		lblServerReply.setText("House not found.  Try again!");
            	else {
            		lblServerReply.setText(result + " loading...");
                }
            }
            
            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };
        
        // Listen for the button clicks
        btnMeal.addClickListener(new ClickListener(){
            public void onClick(Widget w) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                MealServiceUsageExample.getService().getMeals("dummy", callback);
            }
        });
    }
    
    public static GWTLoginServiceAsync getService(){
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of
        // the interface. The cast is always safe because the generated proxy
        // implements the asynchronous interface automatically.
        GWTLoginServiceAsync service = (GWTLoginServiceAsync) GWT.create(GWTLoginService.class);
        // Specify the URL at which our service implementation is running.
        // Note that the target URL must reside on the same domain and port from
        // which the host page was served.
        //
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "gwtloginservice";
        endpoint.setServiceEntryPoint(moduleRelativeURL);
        return service;
    }
}
