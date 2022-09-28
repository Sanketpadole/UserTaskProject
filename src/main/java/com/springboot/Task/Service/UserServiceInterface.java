package com.springboot.Task.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.UsersDto;
import com.springboot.Task.Entity.Users;

@Service
public interface UserServiceInterface {

	Users FindByEmail(String email);

	List<UsersDto> getAllUser();

	void deleteUser(Long userId);

	UsersDto updateUser(UsersDto userDto, Long id);

	UsersDto getUserById(Long id);

	Users creatUser(UsersDto userDto);

}
