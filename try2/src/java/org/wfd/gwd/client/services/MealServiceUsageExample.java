/*
 * MealServiceUsageExample.java
 *
 * Created on April 27, 2008, 10:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.wfd.gwd.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.wfd.gwd.client.GWTLoginServiceUsageExample;

/**
 * Example class using the MealService service. 
 *
 * @author tejash
 */
public class MealServiceUsageExample extends VerticalPanel {

    private Label lblServerReply = new Label();
    private TextBox txtUserInput = new TextBox();
    private Button btnSend = new Button("Login");

    public MealServiceUsageExample() {
        add(new Label("Enter House: "));
        add(txtUserInput);
        add(btnSend);
        add(lblServerReply);

        // Create an asynchronous callback to handle the result.
        final AsyncCallback callback = new AsyncCallback() {

            public void onSuccess(Object result) {
                RootPanel.get().clear();
                RootPanel.get().add(new GWTLoginServiceUsageExample());
//                lblServerReply.setText((String)result + " hello ");
            }

            public void onFailure(Throwable caught) {
                lblServerReply.setText(GWT.getModuleBaseURL() + " Communication failed" + caught.getMessage());
                caught.printStackTrace();
            }
        };

        // Listen for the button clicks
        btnSend.addClickListener(new ClickListener() {

            public void onClick(Widget w) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                getService().getMeals(txtUserInput.getText(), callback);
            }
        });
    }

    public static MealServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of
        // the interface. The cast is always safe because the generated proxy
        // implements the asynchronous interface automatically.
        MealServiceAsync service = (MealServiceAsync) GWT.create(MealService.class);
        // Specify the URL at which our service implementation is running.
        // Note that the target URL must reside on the same domain and port from
        // which the host page was served.
        //
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "services/mealservice";
        endpoint.setServiceEntryPoint(moduleRelativeURL);
        return service;
    }
}
