package com.zerobase.cms.user.client;

import com.zerobase.cms.user.client.mailgun.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun") // 명확히 지정된 이름표 (스프링 빈이 여러 개 있을 경우, 특정 빈을 명확히 지정)
public interface MailgunClient {

	@PostMapping("sandbox968bcb0dd0ec46bcb0e0f6078c5b5edc.mailgun.org/messages") // 특정 경로로 POST 요청
	String sendEmail(@SpringQueryMap SendMailForm form);
	// @SpringQueryMap: SendMailForm 객체를 쿼리스트링으로 변환하여 요청
	// Response: Feign의 기본 응답 객체(호출 후 값 저장)

}