/*
 * GWTLoginService.java
 *
 * Created on April 29, 2008, 9:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.wfd.gwd.client;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 *
 * @author tejash
 */
public interface GWTLoginService extends RemoteService{
    public String myMethod(String s);
}
