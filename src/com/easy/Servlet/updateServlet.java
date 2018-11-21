package com.easy.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easy.util.JDBCUtil;

/**
 * Servlet implementation class updateServlet
 */
@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public updateServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		
		String sql = "update person set username=? ,password=?,sex=? where no=?";
	    JDBCUtil.execute(sql,username,password,sex,no);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
