package com.biz.sec.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserDao uDao;
	private final PasswordEncoder encPassword;
	
	public void insert(Optional<UserVO> opUserVO) {
		
		// 필요한 정보 추출
		UserVO userVO = opUserVO.get();
		
		// vo로부터 password를 추출하여 BCrypt 암호화를 한 후
		// 다시 VO에 setting하고 Dao.save()로 전달
		String strPassword = userVO.getPassword();
		String strEncPassword = encPassword.encode(strPassword);
		userVO.setPassword(strEncPassword);
		userVO.setEnabled(true);
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setCredentialsNonExpired(true);
		
		UserVO retUserVO = uDao.save(userVO);
		
	}

	
	
	
}
