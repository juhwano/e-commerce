package com.zerobase.cms.user.service;

import static org.junit.jupiter.api.Assertions.*;

import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SignUpCustomerServiceTest {

	@Autowired
	private SignUpCustomerService service;

	@Test
	void signUp() {
	    // given
		SignUpForm form = SignUpForm.builder()
			.name("name")
			.birth(LocalDateTime.now())
			.email("abc@gmail.com")
			.password("1")
			.phone("01012345678")
			.build();
	    // when
		Customer customer = service.signUp(form);

		// then
		assertNotNull(customer.getId(), "Customer ID should not be null after signup.");
	}

}