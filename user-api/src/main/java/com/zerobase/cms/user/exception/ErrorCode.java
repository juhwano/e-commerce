package com.zerobase.cms.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	ALREADY_REGISTERED_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
	WRONG_VERIFICATION(HttpStatus.BAD_REQUEST, "잘못된 인증 시도입니다."),
	EXPIRE_CODE(HttpStatus.BAD_REQUEST, "인증 시간이 만료되었습니다."),
	NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "가입되지 않은 회원입니다."),

	//login
	LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "아이디나 패스워드를 확인해주세요"),


	ALREADY_VERIFY(HttpStatus.BAD_REQUEST, "이미 인증이 완료되었습니다.");


	private final HttpStatus httpStatus;
	private final String detail;
}
