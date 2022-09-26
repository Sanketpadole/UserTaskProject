package com.springboot.Task.ServiceImpl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.springboot.Task.Dto.UsersDto;
import com.springboot.Task.Entity.Users;
import com.springboot.Task.Repository.AuthRepository;

import com.springboot.Task.Service.AuthInterface;

@Service
public class AuthServiceImpl implements AuthInterface, UserDetailsService {
	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RolePermissionServiceImpl rolePermissionServiceImpl;
	
	
	

	@Override
	public void registerUser(UsersDto usersDto) {
		Users users = new Users();
		users.setUsername(usersDto.getUsername());
		String password = this.passwordEncoder.encode(usersDto.getPassword());

		users.setPassword(password);
		users.setEmail(usersDto.getEmail());

		this.authRepository.save(users);

	}

	public boolean comparePassword(String password, String hashPassword) {

		return passwordEncoder.matches(password, hashPassword);
	}

	public UserDetails loadUserByUsername(String username) {
		Users users;
		{
			users = this.authRepository.findByUsername(username);
			System.out.println("wrhj"+users);
			if (users == null) {
				System.out.println("user not found");
			}
		}
//		return users;
		return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(),
				getAuthority(users));
	}
	
	
	private ArrayList<SimpleGrantedAuthority> getAuthority(Users user) {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		if ((user.getId() + "permission") != null) {
			ArrayList<SimpleGrantedAuthority> authorities1 = new ArrayList<>();
			System.out.println("efjh");

			ArrayList<String> permissions = this.rolePermissionServiceImpl.getPermissionByUserId(user.getId());

			permissions.forEach(e -> {
				authorities1.add(new SimpleGrantedAuthority("ROLE_" + e));

			});
			authorities = authorities1;

		}
		return authorities;
	}

	

	
	
	
	


}