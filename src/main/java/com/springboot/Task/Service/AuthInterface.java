package com.springboot.Task.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.UsersDto;

@Service
public interface AuthInterface {

	void registerUser(UsersDto usersDto);

	Boolean comparePassword(String password, String hashPassword);

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
