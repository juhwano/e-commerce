package com.zerobase.cms.user.service;

import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.repository.CustomerRepository;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

	private final CustomerRepository customerRepository;

	public Customer signUp(SignUpForm form) {
		return customerRepository.save(Customer.from(form));
	}

	public boolean isEmailExists(String email) {
		return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
	}

	public void verifyEmail(String email, String code){
		Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		if(customer.isVerify()){
			throw new CustomException(ErrorCode.ALREADY_VERIFY);
		}
		else if(customer.getVerificationCode().equals(code)){
			throw new CustomException(ErrorCode.WRONG_VERIFICATION);
		}else if(customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
			throw new CustomException(ErrorCode.EXPIRE_CODE);
		}
		customer.setVerify(true);

	}

	/**
	 * 저장하는 법 1. @Transactional: JPA에서 제공하는 어노테이션으로, 트랜잭션을 시작하고 종료하는 역할을 합니다. 메서드에 @Transactional
	 * 어노테이션을 붙이면, 메서드가 실행될 때 트랜잭션이 시작되고, 메서드가 정상적으로 종료되면 트랜잭션이 커밋됩니다. 만약 예외가 발생하면 트랜잭션은 롤백됩니다. 2.
	 */
	@Transactional
	public LocalDateTime changeCustomerValidateEamil(Long customerId, String verificationCode) {
		Optional<Customer> customerOptional = customerRepository.findById(customerId);

		if (customerOptional.isPresent()) {
			Customer customer = customerOptional.get();
			customer.setVerificationCode(verificationCode);
			customer.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
			return customer.getVerifyExpiredAt();
		}
		throw new CustomException(ErrorCode.NOT_FOUND_USER);
	}

}
