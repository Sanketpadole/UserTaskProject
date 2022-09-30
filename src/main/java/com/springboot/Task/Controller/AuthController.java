package com.springboot.Task.Controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Task.Dto.ErrorResponseDto;
import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Dto.UsersDto;
import com.springboot.Task.Entity.LoggerEntity;
import com.springboot.Task.Entity.Users;
import com.springboot.Task.Page.PasswordValidator;
import com.springboot.Task.Repository.AuthRepository;
import com.springboot.Task.Repository.LoggerRepository;
import com.springboot.Task.Security.JwtTokenUtil;
import com.springboot.Task.Service.AuthInterface;
import com.springboot.Task.ServiceImpl.AuthServiceImpl;

@RestController
@RequestMapping
public class AuthController {
	@Autowired
	private AuthInterface authInterface;
	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private AuthServiceImpl authServiceImpl;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private LoggerRepository loggerRepositoty;

	@PostMapping("/register")

	ResponseEntity<?> registerUser(@RequestBody UsersDto usersDto) {
		String password = usersDto.getPassword();
		if (PasswordValidator.isValid(password)) {

			this.authInterface.registerUser(usersDto);
			return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
					HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("Password not valid", password),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")

	ResponseEntity<?> loginUser(@RequestBody UsersDto usersDto) {

		try {
			Users users = new Users();

			users = this.authRepository.findByEmail(usersDto.getEmail());
			String password = usersDto.getPassword();
			if (PasswordValidator.isValid(password)) {

				if (this.authServiceImpl.comparePassword(password, users.getPassword())) {

					final UserDetails userDetails = this.authServiceImpl.loadUserByUsername(usersDto.getEmail());

					users = this.authRepository.findByEmail(usersDto.getEmail());

					final String token = jwtTokenUtil.generateToken(userDetails);

					LoggerEntity logger = new LoggerEntity();
					logger.setToken(token);
					logger.setUserId(users);
					Calendar calendar = Calendar.getInstance();

					calendar.add(Calendar.HOUR_OF_DAY, 5);

					logger.setExpireAt(calendar.getTime());

					this.loggerRepositoty.save(logger);

					return new ResponseEntity<SuccessResponseDto>(
							new SuccessResponseDto("Token Created", "Token", token), HttpStatus.ACCEPTED);

				} else {
					throw new Exception("invalid username");

				}
			} else {
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("Password not valid", password),
						HttpStatus.BAD_REQUEST);

			}

		} catch (Exception e) {
			System.out.println("not found");

		}
		return ResponseEntity.ok("done");

	}
}
