package com.biz.sec;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.repository.BBsDao;

@SpringBootApplication
public class SpBootSecurityV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpBootSecurityV1Application.class, args);
	}
	
	/*
	 * Spring boot에서 프로젝트가 시작될 때 실행할 일들을 작성하는 방법2
	 */
	@Bean
	public CommandLineRunner initBbsData(BBsDao bDao) {
		
		return new CommandLineRunner() {
			
			// 무명 클래스 == inLine class를 사용해 선언
			@Override
			public void run(String... args) throws Exception {
				
				for(int i = 0 ; i < 100 ; i++) {
					LocalDate localDate = LocalDate.now();
					LocalDateTime lt = LocalDateTime.now();

					String title = String.format("%s에 작성된 글입니다.", lt.toString());
					String date = localDate.toString();
					
					BBsVO bbsVO = BBsVO.builder().bbsTitle(title).bbsAuth("hyerin.you").bbsDate(date).build();
					bDao.save(bbsVO);
				}
				
			}
		};
		
		
	}

}
