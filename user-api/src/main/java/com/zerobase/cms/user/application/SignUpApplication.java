package com.zerobase.cms.user.application;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import com.zerobase.cms.user.service.SignUpCustomerService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

	private final MailgunClient mailgunClient;
	private final SignUpCustomerService signUpCustomerService;

	public void customerVerify(String email, String code){
		signUpCustomerService.verifyEmail(email, code);
	}

	public String customerSignUp(SignUpForm form) {
		if (signUpCustomerService.isEmailExists(form.getEmail())) {
			throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
		} else {
			Customer c = signUpCustomerService.signUp(form);
			LocalDateTime now = LocalDateTime.now();

			String code = getRandomCode();

			SendMailForm sendMailForm = SendMailForm.builder()
				.from("Excited User <USER@sandbox968bcb0dd0ec46bcb0e0f6078c5b5edc.mailgun.org>")
				.to("calculus9006@gmail.com")
				.subject("verification Email")
				.text(getVerificationEmailBody(form.getEmail(), form.getName(), getRandomCode()))
				.build();
			mailgunClient.sendEmail(sendMailForm);
			signUpCustomerService.changeCustomerValidateEamil(c.getId(), code);
			return "회원가입에 성공하셨습니다.";
		}
	}

	private String getRandomCode() {
		return RandomStringUtils.random(19, true, true);
	};

	private String getVerificationEmailBody(String email, String name, String code) {
		StringBuilder builder = new StringBuilder();
		return builder.append("Hello! ").append(name)
			.append("! Please Click Link for verification.\n\n")
			.append("http://localhost:8081/signup/verify/customer?email=")
			.append(email)
			.append("&code=")
			.append(code).toString();
	}

}
