<%-- 
    Document   : index
    Created on : Apr 26, 2008, 1:32:13 AM
    Author     : tejash
--%>

<jsp:declaration>
        import org.wfd.util.HibernateUtil;
        import org.hibernate.SessionFactory;
        
        HibernateUtil.getSessionFactory();
        
        
</jsp:declaration>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Hello World!</h2>
    </body>
    
    <%
    SessionFactory session2 = HibernateUtil.getSessionFactory();
    
    %>
        
</html>
