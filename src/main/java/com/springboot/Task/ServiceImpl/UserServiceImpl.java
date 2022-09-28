package com.springboot.Task.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.ITaskEntityDto;
import com.springboot.Task.Dto.UsersDto;
import com.springboot.Task.Entity.TaskEntity;
import com.springboot.Task.Entity.Users;
import com.springboot.Task.Page.Pagination;
import com.springboot.Task.Repository.AuthRepository;
import com.springboot.Task.Repository.TaskEntityRepository;
import com.springboot.Task.Service.UserServiceInterface;

import Exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private TaskEntityRepository taskEntityRepository;

	@Override
	public Users FindByEmail(String email) {
		Users user = this.authRepository.findByEmail(email);
		return user;

	}

	@Override
	public List<UsersDto> getAllUser() {
		List<Users> user = this.authRepository.findAll();
		List<UsersDto> save = user.stream().map(e -> this.userToDto(e)).collect(Collectors.toList());
		return save;

	}

	@Override
	public void deleteUser(Long userId) {
		System.out.println("dfgh");
		this.authRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found With Id :" + userId));
		System.out.println("csDvs");
		this.authRepository.deleteById(userId);
		System.out.println("xzgbkfxvffu");

	}

	@Override
	public UsersDto updateUser(UsersDto userDto, Long id) {
		Users user = this.authRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id :" + id));
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		authRepository.save(user);
		return userDto;

	}
	
	
	public Page<ITaskEntityDto> getAllRoles(String search, String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		if  (search == null)  {
			

			return taskEntityRepository.findByOrderById(paging, ITaskEntityDto.class);

		} else {
			System.out.println("fadkh");
			return taskEntityRepository.findBydescription(search, paging, ITaskEntityDto.class);
			
		
	}
	}
	
	
	
	
	

	@Override
	public UsersDto getUserById(Long id) {
		System.out.println("afj");
		Users user = this.authRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found With ID :" + id));
		return this.userToDto(user);

	}

	@Override
	public Users creatUser(UsersDto userDto) {
		Users user = new Users();
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		// return "User Registration successful......!!!!!!!!!!!!!!";

		return this.authRepository.save(user);

	}

	private UsersDto userToDto(Users user) {
		UsersDto userDto = this.modelMapper.map(user, UsersDto.class);
		return userDto;
	}

//
//	private Users dtoToUser(UsersDto userDto)
//	{
//		//convert dto to user --1 add source class?,class object add 
//		Users user= this.modelMapper.map(userDto,Users.class );
//		return user;
//	}
//
//	private UsersDto userToDto(Users user)
//	{
//		UsersDto userDto=this.modelMapper.map(user,UsersDto.class);
//		return userDto;
//	}
//
//	//post user new create
//	@Override
//	public Users creatUser(UsersDto userDto) {
//		Users user=new Users();
//		user.setEmail(userDto.getEmail());
//		user.setUsername(userDto.getUsername());
//		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//		//return "User Registration successful......!!!!!!!!!!!!!!";
//
//		return this.authRepository.save(user);
//
//
//
//
//	}
//
//	@Override
//	public UsersDto getUserById(Long userId)
//	{
//		System.out.println("afj");
//		Users user=this.authRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found With ID :" +userId)) ;
//		return this.userToDto(user);
//	}
//
//	//update User
//	@Override
//	public UsersDto updateUser(UsersDto userDto, Long id) {
//		Users user= this.authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with Id :"+id));
//		user.setUsername(userDto.getUsername());
//		authRepository.save(user);
//		return userDto;
//
//	}
//
//	@Override
//	public void deleteUser(Long userId)
//	{
//		this.authRepository.findById(userId).orElseThrow( () ->
//		new ResourceNotFoundException("User Not Found With Id :"+userId));
//		this.authRepository.deleteById(userId);
//	}
//
//	@Override
//	public List<UsersDto> getAllUser() {
//		List<Users> user= this.authRepository.findAll();
//		List<UsersDto>save=user.stream().map(e  -> this.userToDto(e)).collect(Collectors.toList());
//		return save;
//
//
//	}
//
//	@Override
//	public Users FindByEmail(String email) {
//		Users user =this.authRepository.findByEmail(email);
//		return  user;
//
//	}
}
