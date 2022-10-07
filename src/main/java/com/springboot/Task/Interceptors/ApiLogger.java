package com.springboot.Task.Interceptors;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.springboot.Task.Service.ApiLoggerSerivce;
import com.springboot.Task.Service.LoggerService;

@Component
public class ApiLogger implements HandlerInterceptor {

	public ApiLogger() {

	}

	@Autowired
	private ApiLoggerSerivce apiLoggerSerivceInterface;

	@Autowired
	private LoggerService loggerService;

	Gson gson = new Gson();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		ArrayList<String> skipUrls = new ArrayList<>(Arrays.asList("/login", "/register", "/v3/api-docs",
				"/v2/api-docs", "/swagger-resources/**", "/swagger-ui/", "/webjars/", "/api/swagger-ui/index.html"));

		final String arr = request.getRequestURI();

		final String gettoken = request.getHeader("Authorization");

		if (!skipUrls.contains(arr)) {
			final String requestheader = (null != gettoken) ? gettoken.split(" ")[1] : null;

			LoggerEntity logsDetail = loggerService.getLoggerDetail(requestheader);

			if (logsDetail == null) {

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
