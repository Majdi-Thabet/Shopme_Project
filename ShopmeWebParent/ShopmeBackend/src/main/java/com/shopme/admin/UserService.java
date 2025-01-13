package com.shopme.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.admin.user.UserRepository;
import com.shopme.common.entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public List<User> listAll(){
		return (List<User>) repo.findAll()
;	}

}
