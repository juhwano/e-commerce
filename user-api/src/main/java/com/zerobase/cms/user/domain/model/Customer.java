package com.zerobase.cms.user.domain.model;

import com.zerobase.cms.user.domain.SignUpForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;

@Entity // DB 테이블 연결
@Getter
@Builder // 객체 생성시 편리하게 값 설정 가능
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class  Customer extends BaseEntity {

	@Id // 고유 식별자
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy= GenerationType.IDENTITY) // 자동 생성
	private Long id;

	@Column(unique = true) // 중복 방지
	private String email;
	private String name;
	// TODO: 비밀번호 암호화 방안 고민
	private String password;
	// TODO: phone validation 생각해보기
	private String phone;
	private LocalDateTime birth;

	private LocalDateTime verifyExpiredAt;
	private String verificationCode;
	private boolean verify;

	// static 메서드 장점: 코드 간결성, 단점: 오사용
	// Customer 객체 생성
	public static Customer from(SignUpForm form){
		return Customer.builder()
			.email(form.getEmail().toLowerCase(Locale.ROOT)) // 소문자 변환
			.password(form.getPassword())
			.name(form.getName())
			.birth(form.getBirth())
			.phone(form.getPhone())
			.verify(false)
			.build();
	}
}

/**
 * 백엔드 개발자가 공부해야 할 포인트
 * 1. JPA와 엔터티(Entity) 이해:
 * @Entity, @Id, @GeneratedValue와 같은 어노테이션이 데이터베이스와 어떻게 연결되는지 이해하세요.
 * JPA 기본 동작 원리를 학습하세요.
 * 2. 비밀번호 암호화:
 * 안전한 비밀번호 저장을 위해 암호화 라이브러리(예: BCrypt)를 사용해 보세요.
 * 비밀번호를 평문으로 저장하면 보안 사고가 발생할 수 있습니다.
 * 3. 유효성 검사:
 * 전화번호나 이메일 등 데이터를 저장하기 전에 검증하는 방법을 배우세요. (예: 정규식 사용)
 * Spring Validator를 사용하여 필드 유효성을 처리할 수 있습니다.
 * 4. 로컬 날짜와 시간 관리:
 * LocalDateTime은 시간 데이터를 다룰 때 중요한 클래스입니다. 시간대(Time Zone)와 관련된 문제도 학습해 보세요.
 * 5. 빌더 패턴:
 * @Builder는 객체를 유연하고 간결하게 생성할 수 있습니다. 빌더 패턴의 장점과 사용법을 익히세요.
 * 6.Hibernate Envers:
 * 코드에 @AuditOverride가 사용되었습니다. 데이터 변경 기록을 관리하는 Hibernate Envers에 대해 공부하세요.
 *
 * 추가적인 공부 방법
 * Spring Boot 공식 문서를 읽고 따라 하기: JPA와 Hibernate 섹션 추천.
 * 비밀번호 암호화와 관련된 블로그/튜토리얼 검색.
 * 정규식을 활용한 데이터 유효성 검사 실습.
 * 테스트 코드 작성: 이 클래스의 동작을 확인할 수 있는 JUnit 테스트 작성법 배우기.
 */