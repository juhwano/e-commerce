package com.zerobase.cms.user.service;

import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // 비즈니스 로직을 처리
@RequiredArgsConstructor // 자동으로 생성자 주입
public class CustomerService {
    private final CustomerRepository customerRepository; //데이터베이스와 연결되는 역할

    public Optional<Customer> findValidCustomer(String email, String password){
        return customerRepository.findByEmail(email).stream() //리스트를 스트림으로 변환
                .filter(customer -> customer.getPassword().equals(password) && customer.isVerify()) //리스트에서 조건을 만족하는 고객만 걸러냅니다.
                .findFirst(); //필터된 고객 중 첫 번째 고객을 찾습니다. 만약 조건을 만족하는 고객이 없으면 빈(Optional.empty()) 객체를 반환
    }
}
