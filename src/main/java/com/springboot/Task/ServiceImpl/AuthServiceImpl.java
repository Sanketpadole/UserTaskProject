package com.springboot.Task.ServiceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.UsersDto;
import com.springboot.Task.Entity.Users;
import com.springboot.Task.Page.CacheOperation;
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

	@Autowired
	private CacheOperation cache;

	@Override
	public void registerUser(UsersDto usersDto) {
		Users users = new Users();
		users.setUsername(usersDto.getUsername());
		String password = this.passwordEncoder.encode(usersDto.getPassword());

		users.setPassword(password);
		users.setEmail(usersDto.getEmail());

		this.authRepository.save(users);

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user;
		if (!cache.isKeyExist(email, email)) {
			user = authRepository.findByEmail(email);
			System.out.println("from db");
			cache.addInCache(email, email, user);
		} else {

			user = (Users) cache.getFromCache(email, email); // redisTemplate.opsForHash().get(email, email);
			System.out.println("from cache");
		}

		if (user == null) {
			throw new UsernameNotFoundException("User not found with Email: " + email);
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthority(user));
	}

	private ArrayList<SimpleGrantedAuthority> getAuthority(Users user) {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		if (!cache.isKeyExist(user.getId() + "permission", user.getId() + "permission")) {
			ArrayList<SimpleGrantedAuthority> authorities1 = new ArrayList<>();

			ArrayList<String> permissions = this.rolePermissionServiceImpl.getPermissionByUserId(user.getId());

			permissions.forEach(e -> {
				authorities1.add(new SimpleGrantedAuthority("ROLE_" + e));

			});
			authorities = authorities1;

			cache.addInCache(user.getId() + "permission", user.getId() + "permission", authorities1);
		} else {

			authorities = (ArrayList<SimpleGrantedAuthority>) cache.getFromCache(user.getId() + "permission",
					user.getId() + "permission");

		}
		return authorities;
	}

	@Override
	public Boolean comparePassword(String password, String hashPassword) {
		return passwordEncoder.matches(password, hashPassword);
	}
}
