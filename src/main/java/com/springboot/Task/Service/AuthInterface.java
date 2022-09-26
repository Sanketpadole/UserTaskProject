package com.springboot.Task.Service;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.UsersDto;

@Service
public interface AuthInterface {

	void registerUser(UsersDto usersDto);

}
