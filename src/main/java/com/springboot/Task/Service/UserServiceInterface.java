package com.springboot.Task.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.springboot.Task.Dto.UsersDto;

@Service
public interface UserServiceInterface {

	UsersDto getUserId(Long id);

	UsersDto update(UsersDto roleDto, Long id);

	void delete(Long id);

	List<UsersDto> getAll();

}
