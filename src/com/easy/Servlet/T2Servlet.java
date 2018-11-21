package com.easy.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class T2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public T2Servlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1.接取画面上传递过来的数据
				String a = request.getParameter("a");  /*接的第一个值*/
				String b = request.getParameter("b");  /*接的第二个值*/
				//2.将接取过来的数据打印到控制台下
				System.out.println(a); /* 打印接的值*/
				System.out.println(b);
				//2.1将画面上接收的数据提交到数据库
				
				//3.从底层文件跳转到指定画面
				request.getRequestDispatcher("d1.jsp").forward(request, response); /*底层跳画面 d1*/
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
