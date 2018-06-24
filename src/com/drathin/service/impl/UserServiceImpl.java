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
	 * ���췽��˽�л�
	 */
	 private UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	 
	 /**
	  * ����һ��Ψһ��ʵ��
	  */
	 private static UserService Instance = new UserServiceImpl();
	 
	 /**
	  * �ṩһ��������ʵĹ����ӿ�
	  * @return
	  */
	 public static  UserService getInstance() {
		return Instance;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		//���ؽ��
		User user = null;
		//��������
		Connection conn= null;
		try{
				//��ȡ���ݿ�����
				conn = DBUtils.getConnection();
				//�õ�dao��ʵ����Ķ���
				UserDao usersDao = new UserDaoImpl(conn);
				//����dao��ķ���
				user = usersDao.login(username, password);
		}catch(Exception exception ){
			System.out.println("��¼��ѯ�û��ǳ����쳣��"+exception.getMessage());
		}finally {
			DBUtils.closeConnection(conn);
		}		
		return user;		
	}

}
