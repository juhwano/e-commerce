package com.zerobase.cms.user.client.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zerobase.cms.user.client.service.test.EmailSendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

	@SpringBootTest
	class EmailSendServiceTest {

		@Autowired
		private EmailSendService emailSendService;

		@Test
		void EmailTest() {
			// given
			// when
			String response = emailSendService.sendEmail();
			// then
			System.out.println("response = " + response);
			assertNotNull(response); // 응답이 null이 아닌지 확인
			assertTrue(response.contains("Queued"), "Response does not indicate queuing success.");
		}



}