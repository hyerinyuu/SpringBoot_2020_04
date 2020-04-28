package com.biz.hello.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.hello.domain.UserVO;

@Service
public class UserService {

	public List<UserVO> getUserList(){
		List<UserVO> userList = new ArrayList<UserVO>();
		userList.add(UserVO.builder().username("홍길동").password("1234").email("hong@gmail.com").phone("010-1111-1111").address("광주광역시").build());
		userList.add(UserVO.builder().username("이묭룡").password("1234").email("mong@gmail.com").phone("010-2222-2222").address("광주광역시").build());
		userList.add(UserVO.builder().username("성춘향").password("1234").email("hyang@gmail.com").phone("010-3333-3333").address("광주광역시").build());
		
		
		return userList;
	}
	
	
}
