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
				//1.��ȡ�����ϴ��ݹ���������
				String a = request.getParameter("a");  /*�ӵĵ�һ��ֵ*/
				String b = request.getParameter("b");  /*�ӵĵڶ���ֵ*/
				//2.����ȡ���������ݴ�ӡ������̨��
				System.out.println(a); /* ��ӡ�ӵ�ֵ*/
				System.out.println(b);
				//2.1�������Ͻ��յ������ύ�����ݿ�
				
				//3.�ӵײ��ļ���ת��ָ������
				request.getRequestDispatcher("d1.jsp").forward(request, response); /*�ײ������� d1*/
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
