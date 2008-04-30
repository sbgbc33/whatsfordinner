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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Example class using the GWTLoginService service. 
 *
 * @author tejash
 */
public class GWTLoginServiceUsageExample extends VerticalPanel {
    private Label lblServerReply = new Label();
    private TextBox txtUserInput = new TextBox();
    private Button btnSend = new Button("Send to server");
    
    public GWTLoginServiceUsageExample() {
        add(new Label("Input your text: "));
        add(txtUserInput);
        add(btnSend);
        add(lblServerReply);
        
        // Create an asynchronous callback to handle the result.
        final AsyncCallback callback = new AsyncCallback() {
            public void onSuccess(Object result) {
            	if ( result == null )
            		lblServerReply.setText("House not found.  Try again!");
            	else {
            		lblServerReply.setText(result + " loading...");
                        RootPanel.get().clear();
                        RootPanel.get().add(new GWTMainPage(result));
                }
            }
            
            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };
        
        // Listen for the button clicks
        btnSend.addClickListener(new ClickListener(){
            public void onClick(Widget w) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                getService().myMethod(txtUserInput.getText(), callback);
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
