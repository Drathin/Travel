package com.drathin.dao;

import com.drathin.model.User;

public interface UserDao {
	public User login(String username,String passwrod);

}
