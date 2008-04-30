/*
 * GWTLoginServiceImpl.java
 *
 * Created on April 29, 2008, 9:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.wfd.gwd.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.wfd.gwd.client.GWTLoginService;
import org.wfd.model.House;

import sample.Sample;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author tejash
 */
public class GWTLoginServiceImpl extends RemoteServiceServlet implements
		GWTLoginService {

	private String HOUSE_KEY = "house";

	public String myMethod(String s) {

		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		House house = Sample.getHouse(s);

		if (house != null) {
			session.setAttribute(HOUSE_KEY, house);
			return house.getName();
		} else {
			System.err.println("house is null");
			return null;
		}
	}
}
