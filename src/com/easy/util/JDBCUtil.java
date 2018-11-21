package com.easy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.easy.model.Customer;

/**
 * JDBC������
 */
public class JDBCUtil {
	
	/**
	 * ȡ�����ݿ������
	 * @return connection
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName(Constant.DRIVERCLASS);
			connection = DriverManager.getConnection(Constant.URL,Constant.USER,Constant.PASSWORD);
		}catch (Exception e) {
			System.err.println("���ݿ�����ʧ�ܣ�");
			System.err.println(e.getMessage());
		}
		return connection;
	}
	
	/**
	 * �ͷ�JDBC��Դ
	 * 
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public static void release(Connection connection, Statement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.err.println("�ر�resultSetʧ�ܣ�");
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.err.println("�ر�statementʧ�ܣ�");
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("�ر�connectionʧ�ܣ�");
			}
		}
	}
	
	/**
	 * ��ɾ�Ĳ���
	 * 
	 * @param sql: insert, update, delete�� �������� select
	 * @param args ��д SQL ռλ���Ŀɱ����
	 */
	public static void execute(String sql, Object ... args){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i + 1, args[i]);
			}
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.err.println(sql + "����ʧ�ܣ�");
			System.err.println(e.getMessage());
		} finally{
			JDBCUtil.release(connection, preparedStatement, null);
		}
	}
	
	/**
	 * ��ѯ���ݸ���
	 * 
	 * @param sql: select���
	 * @param args ��д SQL ռλ���Ŀɱ����
	 */
	public static int getCount(String sql, Object... args) {
		
		int id = 0;
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
	
		} catch (Exception e) {
			System.err.println(sql + "��ѯʧ�ܣ�");
			System.err.println(e.getMessage());
		} finally {
			JDBCUtil.release(connection, preparedStatement, resultSet);
		}
	
		return id;
	}
	
	/**
	 * ��ѯ�����
	 * 
	 * @param sql: select���
	 * @param args ��д SQL ռλ���Ŀɱ����
	 */
	public static int getMaxId(String sql, Object... args) {
		
		int id = 0;
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
	
		} catch (Exception e) {
			System.err.println(sql + "��ѯʧ�ܣ�");
			System.err.println(e.getMessage());
		} finally {
			JDBCUtil.release(connection, preparedStatement, resultSet);
		}
	
		return id;
	}	
	
	/**
	 * ��ѯ��Ա��Ϣ
	 * 
	 * @param sql: select���
	 * @param args ��д SQL ռλ���Ŀɱ����
	 */
	public static List<Customer> getCustomers(String sql, Object... args) {
		List<Customer> list = null;
		Customer customer = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				customer = new Customer();
				customer.no = resultSet.getInt("no");
				customer.name = resultSet.getString("name");
				customer.score = resultSet.getInt("score");
				customer.tel = resultSet.getString("tel");
				customer.sex = resultSet.getInt("sex");
				list.add(customer);
			}

		} catch (Exception e) {
			System.err.println(sql + "��ѯʧ�ܣ�");
			System.err.println(e.getMessage());
		} finally {
			JDBCUtil.release(connection, preparedStatement, resultSet);
		}

		return list;
	}
}