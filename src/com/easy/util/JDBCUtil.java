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
 * JDBC工具类
 */
public class JDBCUtil {
	
	/**
	 * 取得数据库的连接
	 * @return connection
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName(Constant.DRIVERCLASS);
			connection = DriverManager.getConnection(Constant.URL,Constant.USER,Constant.PASSWORD);
		}catch (Exception e) {
			System.err.println("数据库连接失败！");
			System.err.println(e.getMessage());
		}
		return connection;
	}
	
	/**
	 * 释放JDBC资源
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
				System.err.println("关闭resultSet失败！");
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.err.println("关闭statement失败！");
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("关闭connection失败！");
			}
		}
	}
	
	/**
	 * 增删改操作
	 * 
	 * @param sql: insert, update, delete。 而不包含 select
	 * @param args 填写 SQL 占位符的可变参数
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
			System.err.println(sql + "操作失败！");
			System.err.println(e.getMessage());
		} finally{
			JDBCUtil.release(connection, preparedStatement, null);
		}
	}
	
	/**
	 * 查询数据个数
	 * 
	 * @param sql: select语句
	 * @param args 填写 SQL 占位符的可变参数
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
			System.err.println(sql + "查询失败！");
			System.err.println(e.getMessage());
		} finally {
			JDBCUtil.release(connection, preparedStatement, resultSet);
		}
	
		return id;
	}
	
	/**
	 * 查询最大编号
	 * 
	 * @param sql: select语句
	 * @param args 填写 SQL 占位符的可变参数
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
			System.err.println(sql + "查询失败！");
			System.err.println(e.getMessage());
		} finally {
			JDBCUtil.release(connection, preparedStatement, resultSet);
		}
	
		return id;
	}	
	
	/**
	 * 查询会员信息
	 * 
	 * @param sql: select语句
	 * @param args 填写 SQL 占位符的可变参数
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
			System.err.println(sql + "查询失败！");
			System.err.println(e.getMessage());
		} finally {
			JDBCUtil.release(connection, preparedStatement, resultSet);
		}

		return list;
	}
}