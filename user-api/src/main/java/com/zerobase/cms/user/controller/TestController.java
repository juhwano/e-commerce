package com.zerobase.cms.user.controller;

import com.zerobase.cms.user.client.service.test.EmailSendService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
	private final EmailSendService emailSendService;

	@GetMapping("/test-email")
	public String sendTestEmail(){
		return emailSendService.sendEmail();
	}



}
