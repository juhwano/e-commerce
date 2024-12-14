package com.zerobase.cms.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 스프링 환경에서 간편하게 외부 API를 호출할 수 있는 라이브러리 (스프링의 RestTemplate을 대체, Netflix 에서 개발된 Http client binder)
 * 참고: https://techblog.woowahan.com/2630/
 */
@EnableFeignClients
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
