package com.example.springboot2.Interceptors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.springboot.Task.Dto.ErrorResponseDto;
import com.springboot.Task.Entity.ApiLoggerEntity;
import com.springboot.Task.Entity.LoggerEntity;
import com.springboot.Task.Repository.LoggerRepository;
import com.springboot.Task.Service.ApiLoggerSerivce;


//import com.example.springboot2.Dto.ErrorResponseDto;
//import com.example.springboot2.Entities.ApiLoggerEntity;
//import com.example.springboot2.Entities.LoggerEntity;
//import com.example.springboot2.ServiceInterface.ApiLoggerSerivce;
//import com.example.springboot2.ServiceInterface.LoggerService;
//import com.google.gson.Gson;

@Component
public class ApiLogger implements HandlerInterceptor {

	public ApiLogger() {

	}

	@Autowired
	private ApiLoggerSerivce apiLoggerSerivceInterface;

	@Autowired
	private LoggerRepository loggerRepository;

	Gson gson = new Gson();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		ArrayList<String> skipUrls = new ArrayList<>(
				Arrays.asList("/api/v1/auth/login", "/api/v1/auth/register", "/v3/api-docs", "/v2/api-docs",
						"/swagger-resources/**", "/swagger-ui/", "/webjars/", "/api/swagger-ui/index.html"));

		final String arr = request.getRequestURI();

		final String gettoken = request.getHeader("Authorization");

		System.out.println("tok" + gettoken);
		if (!skipUrls.contains(arr)) {
			final String requestheader = (null != gettoken) ? gettoken.split(" ")[1] : null;

//			LoggerEntity logsDetail = loggerServiceInterface.getLoggerDetail(requestheader);
			LoggerEntity logger = loggerRepository.findByToken(requestheader);
			if (logger == null) {

				ErrorResponseDto error = new ErrorResponseDto("You are not login User", "notLoginUser");
				String employeeJsonString = this.gson.toJson(error);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write(employeeJsonString);

				return false;

			} else

			{
				ApiLoggerEntity apiDetail = new ApiLoggerEntity();

				apiDetail.setUserToken(gettoken);
				System.out.println("not" + apiDetail);
				apiDetail.setIpAddress(request.getRemoteAddr());
				apiDetail.setUrl(request.getRequestURI());
				apiDetail.setMethod(request.getMethod());
				apiDetail.setHost(request.getRemoteHost());
				apiDetail.setBody(request instanceof StandardMultipartHttpServletRequest ? null
						: request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
				apiLoggerSerivceInterface.createApiLog(apiDetail);
				return true;

			}

		} else {
			ApiLoggerEntity apiDetail = new ApiLoggerEntity();
			apiDetail.setUserToken(" ");
			apiDetail.setIpAddress(request.getRemoteAddr());
			apiDetail.setUrl(request.getRequestURI());
			apiDetail.setMethod(request.getMethod());
			apiDetail.setHost(request.getRemoteHost());

			apiDetail.setBody(request instanceof StandardMultipartHttpServletRequest ? null
					: request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));

			apiLoggerSerivceInterface.createApiLog(apiDetail);
			return true;

		}

	}
}
