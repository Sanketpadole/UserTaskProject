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
import com.springboot.Task.Dto.LoggerDto;
import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Dto.UsersDto;

import com.springboot.Task.Entity.Users;
import com.springboot.Task.Page.PasswordValidator;
import com.springboot.Task.Repository.AuthRepository;
import com.springboot.Task.Repository.LoggerRepository;
import com.springboot.Task.Security.JwtTokenUtil;
import com.springboot.Task.Service.AuthInterface;
import com.springboot.Task.Service.LoggerService;
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
	private LoggerService loggerService;

	@PostMapping("/register")

	ResponseEntity<?> registerUser(@RequestBody UsersDto usersDto) {
		String password = usersDto.getPassword();
		String email = usersDto.getEmail();
		if (PasswordValidator.isValid(password) && PasswordValidator.isValidforEmail(email)) {

			this.authInterface.registerUser(usersDto);
			return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
					HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("Password Or Email not valid", password),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")

	ResponseEntity<?> loginUser(@RequestBody UsersDto usersDto) {

		try {
			Users users = new Users();

			users = this.authRepository.findByEmail(usersDto.getEmail());
			System.out.println("falhh"+users);

			String password = usersDto.getPassword();
			System.out.println("fkj"+password);
			if (PasswordValidator.isValid(password)) {

				if (this.authServiceImpl.comparePassword(password, users.getPassword())) {

					final UserDetails userDetails = this.authServiceImpl.loadUserByUsername(usersDto.getEmail());
					System.out.println("qkj");
					users = this.authRepository.findByEmail(usersDto.getEmail());
					System.out.println("qkj"+users);
					final String token = jwtTokenUtil.generateToken(userDetails);

					LoggerDto logger = new LoggerDto();
					logger.setToken(token);

					Calendar calendar = Calendar.getInstance();

					calendar.add(Calendar.HOUR_OF_DAY, 5);

					logger.setExpireAt(calendar.getTime());

					this.loggerService.createlogger(logger, users);

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
			System.out.println("User not found");

		}
		return ResponseEntity.ok("done");

	}
}
