package com.easy.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/T3Servlet")
public class T3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public T3Servlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("a");  /*接的第一个值*/
		String b = request.getParameter("b");  /*接的第二个值*/
		
		
		System.out.println(a);
		System.out.println(b);
		
		request.getRequestDispatcher("d1.jsp").forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
