/*
 * MealService.java
 *
 * Created on April 27, 2008, 10:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.wfd.gwd.client.services;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 *
 * @author tejash
 */
public interface MealService extends RemoteService{
    public String getMeals(String s);
}
