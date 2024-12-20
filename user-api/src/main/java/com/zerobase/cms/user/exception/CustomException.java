package com.zerobase.cms.user.exception;

import lombok.Getter;

/**
 * Exception: 체크된 예외로, 반드시 처리해야 합니다. 처리하지 않으면 컴파일 오류가 발생합니다. (IOException, SQLException, ClassNotFoundException)
 * RuntimeException: 언체크 예외로, 선택적으로 처리할 수 있습니다. 처리하지 않아도 컴파일 오류가 발생하지 않습니다.(NullPointerException, ArrayIndexOutOfBoundsException, IllegalArgumentException)
 *
 */
@Getter
public class CustomException extends RuntimeException{
	private final ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getDetail());
		this.errorCode = errorCode;
	}


}
