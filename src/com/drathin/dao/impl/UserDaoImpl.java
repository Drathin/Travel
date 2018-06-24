package com.drathin.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.drathin.common.DBUtils;
import com.drathin.dao.UserDao;
import com.drathin.model.User;



public class UserDaoImpl implements UserDao {
	Connection connection = null;
	
	public UserDaoImpl(Connection  connection ) {
		// TODO Auto-generated constructor stub
		this.connection = connection ;
	}

	@Override
	public User login(String username, String passwrod) {	
		//声明返回值变量
		User user = null;
		//声明预编译语句对象
		PreparedStatement pStatement = null;
		//声明结果集对象
		ResultSet rSet = null;
		
		try {
			//通过连接建立预编译语句对象
			pStatement = connection.prepareStatement("select * from users where name=? and passwd=?");
			pStatement.setString(1, username);
			pStatement.setString(2, passwrod);
			
			//执行预编译SQL语句
			rSet = pStatement.executeQuery();
			
            //遍历结果集并取出结果集的内容放入user对象
			if(rSet.next()){
				user = new User();
				user.setId(rSet.getInt("id"));
				user.setName(rSet.getString("name"));
				user.setPasswd(rSet.getString("passwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭结果集与语句对象
			DBUtils.closeStatement(rSet, pStatement);
		}
		//返回查询结果的user对象
		return user;
	}

}
