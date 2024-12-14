package com.zerobase.cms.user.client.service.test;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import feign.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 서비스 계층 컴포넌트임을를 알림 (스프링 컨테이너에 의해 관리, 비즈니스 로직 담당)
@RequiredArgsConstructor // 롬복(Lombok)을 사용하여 final 필드에 대해 생성자 자동 주입을 위한 어노테이션
public class EmailSendService {

	/**
	 * Spring에서 @FeignClient로 정의된 MailgunClient가 주입됩니다. final: 이 필드는 클래스가 생성된 후 변경할 수 없음을 의미,
	 *
	 * @RequiredArgsConstructor와 함께 사용시 안전한 의존성 주입 보장
	 */
	private final MailgunClient mailgunClient;

	/**
	 * @RequiredArgsConstructor 어노테이션을 사용하면 생성자를 자동으로 생성해준다. public EmailSendService(MailgunClient
	 * mailgunClient) { this.mailgunClient = mailgunClient; }
	 */

	public String sendEmail() { // 이메일 발송 기능 수행
		/**
		 * SendMailForm 객체 생성
		 * SendMailForm.builder(): 빌더 패턴 사용하여 객체 생성,
		 * 빌더 패턴은 객체 생성 시 가독성을 높이고 필드 값을 설정하는데 유연성 제공
		 */
		SendMailForm form = SendMailForm.builder()
			.from("Excited User <USER@sandbox968bcb0dd0ec46bcb0e0f6078c5b5edc.mailgun.org>")
			.to("calculus9006@gmail.com")
			.subject("제로베이스 이커머스 프로젝트 메일 발송 제목입니다.")
			.text("제로베이스 이커머스 프로젝트 메일 발송 내용입니다.")
			.build();

		return mailgunClient.sendEmail(form);

	}

}
