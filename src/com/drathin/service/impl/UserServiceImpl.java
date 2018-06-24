package com.drathin.service.impl;


import java.sql.Connection;


import com.drathin.common.DBUtils;
import com.drathin.dao.UserDao;
import com.drathin.dao.impl.UserDaoImpl;
import com.drathin.model.User;
import com.drathin.service.UserService;

import sun.security.jca.GetInstance.Instance;

public class UserServiceImpl implements UserService {
	/**
	 * 构造方法私有化
	 */
	 private UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	 
	 /**
	  * 创建一个唯一的实例
	  */
	 private static UserService Instance = new UserServiceImpl();
	 
	 /**
	  * 提供一个对外访问的公共接口
	  * @return
	  */
	 public static  UserService getInstance() {
		return Instance;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		//返回结果
		User user = null;
		//声明连接
		Connection conn= null;
		try{
				//获取数据库连接
				conn = DBUtils.getConnection();
				//拿到dao层实现类的对象
				UserDao usersDao = new UserDaoImpl(conn);
				//调用dao层的方法
				user = usersDao.login(username, password);
		}catch(Exception exception ){
			System.out.println("登录查询用户是出现异常："+exception.getMessage());
		}finally {
			DBUtils.closeConnection(conn);
		}		
		return user;		
	}

}
