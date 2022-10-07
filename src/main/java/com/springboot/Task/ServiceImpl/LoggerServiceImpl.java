package com.springboot.Task.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.LoggerDto;
import com.springboot.Task.Entity.LoggerEntity;
import com.springboot.Task.Entity.Users;
import com.springboot.Task.Page.CacheOperation;
import com.springboot.Task.Repository.LoggerDtoRepository;
import com.springboot.Task.Service.LoggerService;

@Service("LoggerServiceImpl")
public class LoggerServiceImpl implements LoggerService {
	@Autowired
	private LoggerDtoRepository loggerDtoRepository;

	@Autowired
	private CacheOperation cache;

	@Override
	public void createlogger(LoggerDto logger, Users users) {

		LoggerEntity logger1 = new LoggerEntity();
		logger1.setUserId(users);
		logger1.setToken(logger.getToken());
		logger1.setExpireAt(logger.getExpireAt());

		loggerDtoRepository.save(logger1);

	}

//	@Override
//	public LoggerEntity getLoggerDetail(String requestheader) {
//		System.out.println("hello");
//		
//		LoggerEntity logger;
//		if (!cache.isKeyExist(requestheader,requestheader)) {
//		LoggerEntity logger1 = loggerDtoRepository.findByToken(requestheader);
//		cache.addInCache(requestheader,requestheader,logger1);
//		} else {
//			
//			logger = (LoggerEntity) cache.getFromCache(requestheader, requestheader);
//			
//		}
//
//		return logger;
//		
//	}
//	

//	
//	@Override
//	public LoggerEntity getLoggerDetail(String requestheader) {
//
//		LoggerEntity logger;
//
//		if (!cache.isKeyExist(requestheader, requestheader)) {
//
//			logger = loggerDtoRepository.findByToken(requestheader);
//			cache.addInCache(requestheader, requestheader, logger);
//
//		} else {
//
//			logger = (LoggerEntity) cache.getFromCache(requestheader, requestheader);
//
//		}
//
//		return logger;// loggerRepository.findByToken(token);
//
//	}

//	@Override
//	public LoggerEntity getLoggerDetail(String token) {
////		LoggerEntity logger;
////
////		if (!cache.isKeyExist(token, token)) {
//
//		System.out.println("hello");
//			LoggerEntity logger = loggerDtoRepository.findByToken(token);
////			cache.addInCache(token, token, logger);
////
////		} else {
////
////			logger = (LoggerEntity) cache.getFromCache(token, token);
////
////		}
////
////		return logger;// loggerRepository.findByToken(token);
////
////	}
//			return logger;
//
//}
//}

//
//	@Transactional
//	public void logoutUser(String token) {
//
//		final String userToken = token.substring(7);
//
//		LoggerDtoRepository.removeByToken(userToken);
//	}
//
	@Override
	public LoggerEntity getLoggerDetail(String requestheader) {

		System.out.println("hello");
		LoggerEntity logger = loggerDtoRepository.findByToken(requestheader);

		return logger;

	}
}
